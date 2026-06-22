package com.mecano.authservice.repository;

import com.mecano.authservice.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
    Optional<Utilisateur> findByTelephone(String telephone);
    Optional<Utilisateur> findByEmail(String email);
    boolean existsByTelephone(String telephone);
    boolean existsByEmail(String email);
}