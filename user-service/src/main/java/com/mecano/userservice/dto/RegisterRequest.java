package com.mecano.userservice.dto;

import com.mecano.userservice.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @Email(message = "Email invalide")
    @NotBlank(message = "L'email est obligatoire")
    private String email;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 6, message = "Minimum 6 caractères")
    private String motDePasse;

    @NotNull(message = "Le rôle est obligatoire")
    private Role role;

    // Champs spécifiques mécanicien
    private String numeroLicence;
    private String specialite;
    private Double rayonIntervention;
}