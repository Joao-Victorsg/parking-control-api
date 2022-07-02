package com.api.parkingcontrol.services.parkingspot.impl;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repository.ParkingSpotRepository;
import com.api.parkingcontrol.services.parkingspot.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {

    // Controller -> Service -> Repository

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Override
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);
    }

    @Override
    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    @Override
    public Page<ParkingSpotModel> findall(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    @Override
    public Page<ParkingSpotModel> findByResponsibleModelBlock(Pageable pageable, String block){
        return parkingSpotRepository.findByResponsibleModelBlock(pageable,block);
    }

    @Override
    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

    // Its interesting to use @Transactional when the metod changes the data in the db (Save, delete, update for ex).
    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }

    @Override
    public Optional<ParkingSpotModel> findByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.findByParkingSpotNumber(parkingSpotNumber);
    }
}
