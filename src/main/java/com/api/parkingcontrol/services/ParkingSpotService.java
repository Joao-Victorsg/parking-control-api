package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repository.CarModelRepository;
import com.api.parkingcontrol.repository.ParkingSpotRepository;
import com.api.parkingcontrol.repository.ResponsibleModelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService{

    // Controller -> Service -> Repository

    final ParkingSpotRepository parkingSpotRepository;
    final CarModelRepository carModelRepository;
    final ResponsibleModelRepository responsibleModelRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository, CarModelRepository carModelRepository,
                              ResponsibleModelRepository responsibleModelRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.carModelRepository = carModelRepository;
        this.responsibleModelRepository = responsibleModelRepository;
    }

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return carModelRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return responsibleModelRepository.existsByApartmentAndBlock(apartment,block);
    }

    public Page<ParkingSpotModel> findall(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

    // Its interesting to use @Transactional when the metod changes the data in the db (Save, delete, update for ex).
    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }
}
