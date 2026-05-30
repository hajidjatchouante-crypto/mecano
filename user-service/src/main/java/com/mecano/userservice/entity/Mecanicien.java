package com.mecano.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mecaniciens")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mecanicien {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    private String numeroLicence;
    private String specialite;
    private Double rayonIntervention;
    private Double latitude;
    private Double longitude;
    private Boolean disponible;
    private Double noteMoyenne;
}