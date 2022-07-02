package com.api.parkingcontrol.services.car.impl;

import com.api.parkingcontrol.repository.CarRepository;
import com.api.parkingcontrol.services.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return carRepository.existsByLicensePlateCar(licensePlateCar);
    }
}
