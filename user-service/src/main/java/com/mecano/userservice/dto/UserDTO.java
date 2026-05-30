package com.mecano.userservice.dto;

import com.mecano.userservice.enums.Role;
import com.mecano.userservice.enums.Statut;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Role role;
    private Statut statut;
    private LocalDateTime createdAt;
}