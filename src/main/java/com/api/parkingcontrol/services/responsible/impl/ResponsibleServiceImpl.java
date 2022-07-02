package com.api.parkingcontrol.services.responsible.impl;

import com.api.parkingcontrol.repository.ResponsibleRepository;
import com.api.parkingcontrol.services.responsible.ResponsibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponsibleServiceImpl implements ResponsibleService {

    @Autowired
    private ResponsibleRepository responsibleRepository;

    @Override
    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return responsibleRepository.existsByApartmentAndBlock(apartment,block);
    }
}
