package com.example.locadora_de_jogos.service;

import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.example.locadora_de_jogos.model.JogadorModel;
import com.example.locadora_de_jogos.repository.JogadorRepository;

@Service
@RequiredArgsConstructor
public class JogadorDetailsService implements UserDetailsService {

    private final JogadorRepository jogadorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        JogadorModel jogador = jogadorRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Jogador não encontrado com email: " + email));
        GrantedAuthority authority = new SimpleGrantedAuthority(jogador.getRole());
        return new User(jogador.getEmail(), jogador.getSenha(), Collections.singleton(authority));
    }
}

