package com.api.parkingcontrol.repository;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {

//Extending the JpaRepository already brings the basic methods for doing queries in the db, like
//findByID, findAll, select, etc.

//At first, it isn't obligatory to put the @Repository annotation, because the JpaRepository already has it,
// and as ParkingSpotRepository is extending this class, it automatically gains the annotation. But as learning
// purpose I will put it there.

    boolean existsByParkingSpotNumber(String parkingSpotNumber);

    Page<ParkingSpotModel> findByResponsibleModelBlock(Pageable pageable, String block);
}
