package com.mecano.authservice.entity;

import com.mecano.authservice.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auth_utilisateurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String telephone;

    private String email;
    private String motDePasse;
    private String nom;
    private String prenom;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean actif;
    private boolean profileComplete;

    // Étape d'inscription (1, 2 ou 3)
    private Integer etapeInscription;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        etapeInscription = 1;
        actif = true;
        profileComplete = false;
    }
}