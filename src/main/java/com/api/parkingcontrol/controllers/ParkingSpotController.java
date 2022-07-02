package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ParkingSpotDtoRequest;
import com.api.parkingcontrol.dtos.ParkingSpotDtoResponse;
import com.api.parkingcontrol.mappers.ParkingSpotMapper;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.car.CarService;
import com.api.parkingcontrol.services.parkingspot.ParkingSpotService;
import com.api.parkingcontrol.services.responsible.ResponsibleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

     private ParkingSpotService parkingSpotService;
     private CarService carService;
     private ResponsibleService responsibleService;
     private final ParkingSpotMapper mapper;

    @Autowired
    public ParkingSpotController(ParkingSpotService parkingSpotService, CarService carService,
                                 ResponsibleService responsibleService, ParkingSpotMapper mapper) {
        this.parkingSpotService = parkingSpotService;
        this.carService = carService;
        this.responsibleService = responsibleService;
        this.mapper = mapper;
    }

    @ApiOperation("Save one parking spot")
    @PostMapping(produces = "application/json")
    // The @Valid notation is used to do the validations that are setted up in the DTO.
    // The @RequestBody is used to define the fields of the request.
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDtoRequest parkingSpotDto){

        ResponseEntity<Object> CONFLICT = verifyIfAlreadyInUseOrRegistered(parkingSpotDto);
        if (CONFLICT != null) return CONFLICT;

        var request = this.mapper.toParkingSpotModel(parkingSpotDto);

        var response = this.mapper.toParkingSpotDtoResponse(
                parkingSpotService.save(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private ResponseEntity<Object> verifyIfAlreadyInUseOrRegistered(ParkingSpotDtoRequest parkingSpotDto) {
        if(carService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");

        if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");

        if(responsibleService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for " +
                    "this apartment/block!");
        return null;
    }

    @ApiOperation("Return a list of parking spots")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200", description = "Return a list of parking spots"),
        @ApiResponse(responseCode = "404", description = "No parking spots found to this block or there aren't any parking spot registered"),
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<ParkingSpotDtoResponse>> getAllParkingSpots(@PageableDefault(page = 0, size=10, sort="id",
            direction = Sort.Direction.ASC) Pageable pageable, @RequestParam(required = false) Optional<String> block){

        if(block.isPresent()){

            Page<ParkingSpotDtoResponse> response = convertModelToDtoResponse(parkingSpotService.
                    findByResponsibleModelBlock(pageable,block.get())
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        Page<ParkingSpotDtoResponse> response = convertModelToDtoResponse(parkingSpotService.findall(pageable));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public PageImpl<ParkingSpotDtoResponse> convertModelToDtoResponse(Page<ParkingSpotModel> result){

        return new PageImpl<>(
                result.stream().
                map(item -> mapper.toParkingSpotDtoResponse(item)).
                collect(Collectors.toList())
        );
    }

    @ApiOperation("Get one parking Spot by id")
    @GetMapping(path = "/{id}",produces = "application/json")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        var response = mapper.toParkingSpotDtoResponse(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation("Get one parking Spot by parking spot number")
    @GetMapping(path ="/number/{parkingSpotNumber}",produces = "application/json")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "parkingSpotNumber") String parkingSpotNumber){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findByParkingSpotNumber(parkingSpotNumber);
        if(!parkingSpotModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        var response = mapper.toParkingSpotDtoResponse(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path ="/{id}",produces = "application/json")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");

        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted successfully.");
    }

    @PutMapping(path ="/{id}",produces = "application/json")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid ParkingSpotDtoRequest parkingSpotDto){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");

        var request = this.mapper.toParkingSpotModel(parkingSpotDto);

        var response = this.mapper.toParkingSpotDtoResponse(
                parkingSpotService.save(request));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
