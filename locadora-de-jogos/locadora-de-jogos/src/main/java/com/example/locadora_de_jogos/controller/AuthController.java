package com.example.locadora_de_jogos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.locadora_de_jogos.dto.AuthRequest;
import com.example.locadora_de_jogos.dto.AuthResponse;
import com.example.locadora_de_jogos.model.JogadorModel;
import com.example.locadora_de_jogos.security.jwt.JwtTokenProvider;
import com.example.locadora_de_jogos.service.JogadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final JogadorService jogadorService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<JogadorModel> register(@RequestBody AuthRequest request) {
        JogadorModel jogador = jogadorService.registrarJogador(
                request.getApelido(), 
                request.getEmail(), 
                request.getPassword(), 
                "ROLE_USER");
        return ResponseEntity.ok(jogador);
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(org.springframework.security.core.Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(403).body("Usuário não autenticado");
        }
        return ResponseEntity.ok(auth.getAuthorities());
    }
}
