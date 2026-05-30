package com.mecano.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    private String marque;

    @Column(nullable = false)
    private String modele;

    private Integer annee;
    private String immatriculation;
    private String typeCarburant;
}