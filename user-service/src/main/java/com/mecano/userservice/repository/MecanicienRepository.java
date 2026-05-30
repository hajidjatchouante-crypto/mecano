package com.mecano.userservice.repository;

import com.mecano.userservice.entity.Mecanicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MecanicienRepository extends JpaRepository<Mecanicien, String> {
    Optional<Mecanicien> findByUtilisateurId(String utilisateurId);
    List<Mecanicien> findByDisponibleTrue();
    List<Mecanicien> findBySpecialite(String specialite);

    @Query("SELECT m FROM Mecanicien m WHERE " +
            "(6371 * acos(cos(radians(:lat)) * cos(radians(m.latitude)) * " +
            "cos(radians(m.longitude) - radians(:lng)) + " +
            "sin(radians(:lat)) * sin(radians(m.latitude)))) < :rayon")
    List<Mecanicien> findMecaniciensProches(
            @Param("lat") Double latitude,
            @Param("lng") Double longitude,
            @Param("rayon") Double rayon
    );
}