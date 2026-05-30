package com.mecano.userservice.controller;

import com.mecano.userservice.dto.VehiculeDTO;
import com.mecano.userservice.service.VehiculeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final VehiculeService vehiculeService;

    @GetMapping("/{clientId}/vehicules")
    public ResponseEntity<List<VehiculeDTO>> getVehicules(@PathVariable String clientId) {
        return ResponseEntity.ok(vehiculeService.getVehiculesByClient(clientId));
    }

    @PostMapping("/{clientId}/vehicules")
    public ResponseEntity<VehiculeDTO> addVehicule(
            @PathVariable String clientId,
            @RequestBody VehiculeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculeService.addVehicule(clientId, dto));
    }

    @DeleteMapping("/{clientId}/vehicules/{vehiculeId}")
    public ResponseEntity<Void> deleteVehicule(
            @PathVariable String clientId,
            @PathVariable String vehiculeId) {
        vehiculeService.deleteVehicule(vehiculeId);
        return ResponseEntity.noContent().build();
    }
}