package com.mecano.userservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MecanicienDTO {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String numeroLicence;
    private String specialite;
    private Double rayonIntervention;
    private Double latitude;
    private Double longitude;
    private Boolean disponible;
    private Double noteMoyenne;
}