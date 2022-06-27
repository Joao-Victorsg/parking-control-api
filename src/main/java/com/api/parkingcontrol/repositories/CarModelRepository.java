package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarModelRepository extends JpaRepository<CarModel, UUID> {

    boolean existsByLicensePlateCar(String licensePlateCar);

}
