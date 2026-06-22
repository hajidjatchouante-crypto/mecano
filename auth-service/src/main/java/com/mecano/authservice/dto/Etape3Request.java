package com.mecano.authservice.dto;

import com.mecano.authservice.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etape3Request {

    @NotBlank(message = "L'userId est obligatoire")
    private String userId;

    @NotNull(message = "Le rôle est obligatoire")
    private Role role;

    // Champs mécanicien
    private String numeroLicence;
    private String specialite;
    private Double rayonIntervention;

    // Champs client
    private String adresse;
}