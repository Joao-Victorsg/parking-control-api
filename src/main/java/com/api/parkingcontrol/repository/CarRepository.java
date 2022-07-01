package com.api.parkingcontrol.repository;

import com.api.parkingcontrol.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<CarModel, UUID> {

    boolean existsByLicensePlateCar(String licensePlateCar);

}
