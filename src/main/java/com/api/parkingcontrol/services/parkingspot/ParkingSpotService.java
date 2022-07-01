package com.api.parkingcontrol.services.parkingspot;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

public interface ParkingSpotService {

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel);

    public Optional<ParkingSpotModel> findById(UUID id);

    public boolean existsByParkingSpotNumber(String parkingSpotNumber);

    public Page<ParkingSpotModel> findall(Pageable pageable);

    public Page<ParkingSpotModel> findByResponsibleModelBlock(Pageable pageable, String block);

    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel);
}
