package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ResponsibleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResponsibleModelRepository extends JpaRepository<ResponsibleModel, UUID> {
    boolean existsByApartmentAndBlock(String apartment, String block);
}