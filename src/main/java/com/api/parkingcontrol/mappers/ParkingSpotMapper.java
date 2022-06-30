package com.api.parkingcontrol.mappers;

import com.api.parkingcontrol.dtos.ParkingSpotDtoRequest;
import com.api.parkingcontrol.dtos.ParkingSpotDtoResponse;
import com.api.parkingcontrol.models.CarModel;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.models.ResponsibleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface ParkingSpotMapper {

    @Mapping(expression = "java(getCarModel(request))", target="carModel")
    @Mapping(expression = "java(getResponsibleModel(request))", target = "responsibleModel")
    @Mapping(expression = "java(getRegistrationDate())", target = "registrationDate")
    ParkingSpotModel toParkingSpotModel(ParkingSpotDtoRequest request);

    default CarModel getCarModel(ParkingSpotDtoRequest request){
        return new CarModel(request.getLicensePlateCar(),request.getBrandCar(),
                request.getModelCar(),request.getColorCar());
    }

    default ResponsibleModel getResponsibleModel(ParkingSpotDtoRequest request){
        return new ResponsibleModel(request.getResponsibleName(),request.getApartment(),
                request.getBlock());
    }

    default LocalDateTime getRegistrationDate(){
        return LocalDateTime.now(ZoneId.of("UTC"));
    }

    //@Mapping(expression = "java(parkingSpotModel.getCarModel().getLicensePlateCar())", target =  "licensePlateCar")
    @Mapping(expression = "java(getLicensePlateCar(parkingSpotModel))",target = "licensePlateCar")
    @Mapping(expression = "java(getBrandCar(parkingSpotModel))",target = "brandCar")
    @Mapping(expression = "java(getModelCar(parkingSpotModel))",target = "modelCar")
    @Mapping(expression = "java(getColorCar(parkingSpotModel))",target = "colorCar")
    @Mapping(expression = "java(getResponsibleName(parkingSpotModel))",target = "responsibleName")
    @Mapping(expression = "java(getResponsibleApartment(parkingSpotModel))",target = "apartment")
    @Mapping(expression = "java(getResponsibleBlock(parkingSpotModel))",target = "block")
    ParkingSpotDtoResponse toParkingSpotDtoResponse(ParkingSpotModel parkingSpotModel);

    default String getLicensePlateCar(ParkingSpotModel parkingSpotModel){
        return parkingSpotModel.getCarModel().getLicensePlateCar();

    }

    default String getModelCar(ParkingSpotModel parkingSpotModel){
        return parkingSpotModel.getCarModel().getModelCar();
    }

    default String getBrandCar(ParkingSpotModel parkingSpotModel){
        return parkingSpotModel.getCarModel().getBrandCar();
    }

    default String getColorCar(ParkingSpotModel parkingSpotModel){
        return parkingSpotModel.getCarModel().getColorCar();
    }

    default String getResponsibleName(ParkingSpotModel parkingSpotModel){
        return parkingSpotModel.getResponsibleModel().getResponsibleName();
    }

    default String getResponsibleApartment(ParkingSpotModel parkingSpotModel){
        return parkingSpotModel.getResponsibleModel().getApartment();
    }

    default String getResponsibleBlock(ParkingSpotModel parkingSpotModel){
        return parkingSpotModel.getResponsibleModel().getBlock();
    }
}
