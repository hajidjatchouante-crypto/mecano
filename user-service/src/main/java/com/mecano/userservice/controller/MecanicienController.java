package com.mecano.userservice.controller;

import com.mecano.userservice.dto.MecanicienDTO;
import com.mecano.userservice.service.MecanicienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mecaniciens")
@RequiredArgsConstructor
public class MecanicienController {

    private final MecanicienService mecanicienService;

    @GetMapping
    public ResponseEntity<List<MecanicienDTO>> getAllMecaniciens() {
        return ResponseEntity.ok(mecanicienService.getAllMecaniciens());
    }

    @GetMapping("/proches")
    public ResponseEntity<List<MecanicienDTO>> getMecaniciensProches(
            @RequestParam Double lat,
            @RequestParam Double lng,
            @RequestParam(defaultValue = "10.0") Double rayon) {
        return ResponseEntity.ok(mecanicienService.getMecaniciensProches(lat, lng, rayon));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MecanicienDTO> getMecanicienById(@PathVariable String id) {
        return ResponseEntity.ok(mecanicienService.getMecanicienById(id));
    }

    @PatchMapping("/{id}/position")
    public ResponseEntity<MecanicienDTO> updatePosition(
            @PathVariable String id,
            @RequestParam Double lat,
            @RequestParam Double lng) {
        return ResponseEntity.ok(mecanicienService.updatePosition(id, lat, lng));
    }

    @PatchMapping("/{id}/disponibilite")
    public ResponseEntity<MecanicienDTO> updateDisponibilite(
            @PathVariable String id,
            @RequestParam Boolean disponible) {
        return ResponseEntity.ok(mecanicienService.updateDisponibilite(id, disponible));
    }
}