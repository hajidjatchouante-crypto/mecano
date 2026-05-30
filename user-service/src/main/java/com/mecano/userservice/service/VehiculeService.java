package com.mecano.userservice.service;

import com.mecano.userservice.dto.VehiculeDTO;
import com.mecano.userservice.entity.Client;
import com.mecano.userservice.entity.Vehicule;
import com.mecano.userservice.repository.ClientRepository;
import com.mecano.userservice.repository.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final ClientRepository clientRepository;

    public List<VehiculeDTO> getVehiculesByClient(String clientId) {
        return vehiculeRepository.findByClientId(clientId)
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VehiculeDTO addVehicule(String clientId, VehiculeDTO dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        Vehicule vehicule = Vehicule.builder()
                .client(client)
                .marque(dto.getMarque())
                .modele(dto.getModele())
                .annee(dto.getAnnee())
                .immatriculation(dto.getImmatriculation())
                .typeCarburant(dto.getTypeCarburant())
                .build();
        return toDTO(vehiculeRepository.save(vehicule));
    }

    public void deleteVehicule(String vehiculeId) {
        vehiculeRepository.deleteById(vehiculeId);
    }

    private VehiculeDTO toDTO(Vehicule v) {
        return VehiculeDTO.builder()
                .id(v.getId())
                .clientId(v.getClient().getId())
                .marque(v.getMarque())
                .modele(v.getModele())
                .annee(v.getAnnee())
                .immatriculation(v.getImmatriculation())
                .typeCarburant(v.getTypeCarburant())
                .build();
    }
}