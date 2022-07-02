package com.api.parkingcontrol.repository;

import com.api.parkingcontrol.models.ResponsibleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResponsibleRepository extends JpaRepository<ResponsibleModel, UUID> {
    boolean existsByApartmentAndBlock(String apartment, String block);
}
