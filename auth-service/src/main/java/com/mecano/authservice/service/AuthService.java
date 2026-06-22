package com.mecano.authservice.service;

import com.mecano.authservice.dto.*;
import com.mecano.authservice.entity.Utilisateur;
import com.mecano.authservice.repository.UtilisateurRepository;
import com.mecano.authservice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Étape 1 — nom + prénom + âge + téléphone
    public EtapeResponse inscrireEtape1(Etape1Request request) {
        if (utilisateurRepository.existsByTelephone(request.getTelephone())) {
            throw new RuntimeException("Ce numéro de téléphone est déjà utilisé");
        }

        Utilisateur utilisateur = Utilisateur.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .age(request.getAge())
                .telephone(request.getTelephone())
                .etapeInscription(1)
                .actif(true)
                .profileComplete(false)
                .build();

        utilisateur = utilisateurRepository.save(utilisateur);

        return EtapeResponse.builder()
                .userId(utilisateur.getId())
                .message("Étape 1 complétée ! Ajoutez votre email et mot de passe.")
                .prochaineEtape(2)
                .inscriptionComplete(false)
                .build();
    }

    // Étape 2 — email + mot de passe
    public EtapeResponse inscrireEtape2(Etape2Request request) {
        Utilisateur utilisateur = utilisateurRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        utilisateur.setEmail(request.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        utilisateur.setEtapeInscription(2);
        utilisateurRepository.save(utilisateur);

        return EtapeResponse.builder()
                .userId(utilisateur.getId())
                .message("Étape 2 complétée ! Choisissez votre rôle.")
                .prochaineEtape(3)
                .inscriptionComplete(false)
                .build();
    }

    // Étape 3 — rôle + détails
    public AuthResponse inscrireEtape3(Etape3Request request) {
        Utilisateur utilisateur = utilisateurRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        utilisateur.setRole(request.getRole());
        utilisateur.setEtapeInscription(3);
        utilisateur.setProfileComplete(true);
        utilisateurRepository.save(utilisateur);

        return AuthResponse.builder()
                .accessToken(jwtUtil.generateAccessToken(utilisateur))
                .roleToken(jwtUtil.generateRoleToken(utilisateur))
                .userId(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .role(utilisateur.getRole())
                .etapeInscription(3)
                .profileComplete(true)
                .build();
    }

    // Login
    public AuthResponse login(LoginRequest request) {
        Utilisateur utilisateur = utilisateurRepository
                .findByEmail(request.getIdentifiant())
                .orElseGet(() -> utilisateurRepository
                        .findByTelephone(request.getIdentifiant())
                        .orElseThrow(() -> new RuntimeException("Identifiant incorrect")));

        if (!passwordEncoder.matches(request.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return AuthResponse.builder()
                .accessToken(jwtUtil.generateAccessToken(utilisateur))
                .roleToken(jwtUtil.generateRoleToken(utilisateur))
                .userId(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .role(utilisateur.getRole())
                .etapeInscription(utilisateur.getEtapeInscription())
                .profileComplete(utilisateur.isProfileComplete())
                .build();
    }
}