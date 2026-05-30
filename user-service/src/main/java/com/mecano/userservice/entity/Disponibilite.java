package com.mecano.userservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Entity
@Table(name = "disponibilites")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disponibilite {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "mecanicien_id", nullable = false)
    private Mecanicien mecanicien;

    private String jourSemaine;
    private LocalTime heureDebut;
    private LocalTime heureFin;
}