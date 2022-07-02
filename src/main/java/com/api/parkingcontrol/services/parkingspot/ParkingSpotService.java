package com.api.parkingcontrol.services.parkingspot;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

public interface ParkingSpotService {

    @Transactional
    ParkingSpotModel save(ParkingSpotModel parkingSpotModel);

    Optional<ParkingSpotModel> findById(UUID id);

    boolean existsByParkingSpotNumber(String parkingSpotNumber);

    Page<ParkingSpotModel> findall(Pageable pageable);

    Page<ParkingSpotModel> findByResponsibleModelBlock(Pageable pageable, String block);

    @Transactional
    void delete(ParkingSpotModel parkingSpotModel);

    Optional<ParkingSpotModel> findByParkingSpotNumber(String parkingSpotNumber);
}
