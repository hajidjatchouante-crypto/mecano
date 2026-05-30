package com.mecano.userservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculeDTO {
    private String id;
    private String clientId;
    private String marque;
    private String modele;
    private Integer annee;
    private String immatriculation;
    private String typeCarburant;
}