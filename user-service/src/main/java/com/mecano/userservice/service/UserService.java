package com.mecano.userservice.service;

import com.mecano.userservice.dto.*;
import com.mecano.userservice.entity.*;
import com.mecano.userservice.enums.*;
import com.mecano.userservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UtilisateurRepository utilisateurRepository;
    private final ClientRepository clientRepository;
    private final MecanicienRepository mecanicienRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO register(RegisterRequest request) {
        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }

        Utilisateur utilisateur = Utilisateur.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                .role(request.getRole())
                .statut(Statut.ACTIF)
                .build();

        utilisateur = utilisateurRepository.save(utilisateur);

        if (request.getRole() == Role.ROLE_CLIENT) {
            Client client = Client.builder()
                    .utilisateur(utilisateur)
                    .build();
            clientRepository.save(client);
        } else if (request.getRole() == Role.ROLE_MECANICIEN) {
            Mecanicien mecanicien = Mecanicien.builder()
                    .utilisateur(utilisateur)
                    .numeroLicence(request.getNumeroLicence())
                    .specialite(request.getSpecialite())
                    .rayonIntervention(request.getRayonIntervention())
                    .disponible(true)
                    .noteMoyenne(0.0)
                    .build();
            mecanicienRepository.save(mecanicien);
        }

        return toDTO(utilisateur);
    }

    public UserDTO getUserById(String id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return toDTO(utilisateur);
    }

    public UserDTO getUserByEmail(String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return toDTO(utilisateur);
    }

    public List<UserDTO> getAllUsers() {
        return utilisateurRepository.findAll()
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO updateStatut(String id, Statut statut) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        utilisateur.setStatut(statut);
        return toDTO(utilisateurRepository.save(utilisateur));
    }

    public void deleteUser(String id) {
        utilisateurRepository.deleteById(id);
    }

    private UserDTO toDTO(Utilisateur u) {
        return UserDTO.builder()
                .id(u.getId())
                .nom(u.getNom())
                .prenom(u.getPrenom())
                .email(u.getEmail())
                .telephone(u.getTelephone())
                .role(u.getRole())
                .statut(u.getStatut())
                .createdAt(u.getCreatedAt())
                .build();
    }
}