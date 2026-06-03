package com.mecano.authservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank(message = "Le téléphone ou email est obligatoire")
    private String identifiant;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String motDePasse;
}