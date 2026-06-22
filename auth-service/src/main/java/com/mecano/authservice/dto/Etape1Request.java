package com.mecano.authservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etape1Request {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotNull(message = "L'âge est obligatoire")
    @Min(value = 18, message = "Vous devez avoir au moins 18 ans")
    private Integer age;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;
}