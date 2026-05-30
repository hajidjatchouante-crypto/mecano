package com.mecano.userservice.repository;

import com.mecano.userservice.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, String> {
    List<Vehicule> findByClientId(String clientId);
}