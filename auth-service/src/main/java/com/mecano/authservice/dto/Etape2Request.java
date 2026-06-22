package com.mecano.authservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etape2Request {

    @NotBlank(message = "L'userId est obligatoire")
    private String userId;

    @Email(message = "Email invalide")
    @NotBlank(message = "L'email est obligatoire")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 6, message = "Minimum 6 caractères")
    private String motDePasse;
}