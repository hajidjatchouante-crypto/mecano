package com.mecano.userservice.service;

import com.mecano.userservice.dto.MecanicienDTO;
import com.mecano.userservice.entity.Mecanicien;
import com.mecano.userservice.repository.MecanicienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MecanicienService {

    private final MecanicienRepository mecanicienRepository;

    public List<MecanicienDTO> getAllMecaniciens() {
        return mecanicienRepository.findAll()
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MecanicienDTO> getMecaniciensProches(Double lat, Double lng, Double rayon) {
        return mecanicienRepository.findMecaniciensProches(lat, lng, rayon)
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    public MecanicienDTO getMecanicienById(String id) {
        Mecanicien m = mecanicienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mécanicien non trouvé"));
        return toDTO(m);
    }

    public MecanicienDTO updatePosition(String id, Double lat, Double lng) {
        Mecanicien m = mecanicienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mécanicien non trouvé"));
        m.setLatitude(lat);
        m.setLongitude(lng);
        return toDTO(mecanicienRepository.save(m));
    }

    public MecanicienDTO updateDisponibilite(String id, Boolean disponible) {
        Mecanicien m = mecanicienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mécanicien non trouvé"));
        m.setDisponible(disponible);
        return toDTO(mecanicienRepository.save(m));
    }

    private MecanicienDTO toDTO(Mecanicien m) {
        return MecanicienDTO.builder()
                .id(m.getId())
                .nom(m.getUtilisateur().getNom())
                .prenom(m.getUtilisateur().getPrenom())
                .email(m.getUtilisateur().getEmail())
                .telephone(m.getUtilisateur().getTelephone())
                .numeroLicence(m.getNumeroLicence())
                .specialite(m.getSpecialite())
                .rayonIntervention(m.getRayonIntervention())
                .latitude(m.getLatitude())
                .longitude(m.getLongitude())
                .disponible(m.getDisponible())
                .noteMoyenne(m.getNoteMoyenne())
                .build();
    }
}