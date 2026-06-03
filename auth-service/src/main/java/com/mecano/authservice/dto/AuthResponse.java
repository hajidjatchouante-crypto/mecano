package com.mecano.authservice.dto;

import com.mecano.authservice.enums.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    // Token 1 — infos utilisateur (15 min)
    private String accessToken;

    // Token 2 — rôle (7 jours)
    private String roleToken;

    private String userId;
    private String nom;
    private String prenom;
    private Role role;
    private Integer etapeInscription;
    private boolean profileComplete;
}