package com.mecano.authservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtapeResponse {
    private String userId;
    private String message;
    private Integer prochaineEtape;
    private boolean inscriptionComplete;
}