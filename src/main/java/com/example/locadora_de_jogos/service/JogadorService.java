package com.example.locadora_de_jogos.service;

import com.example.locadora_de_jogos.model.JogadorModel;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.locadora_de_jogos.repository.JogadorRepository;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<JogadorModel> listar() {
        return jogadorRepository.findAll();
    }

    public Optional<JogadorModel> buscarPorId(Integer id) {
        return jogadorRepository.findById(id);
    }
    
    public Optional<JogadorModel> buscarPorEmail(String email) {
        return jogadorRepository.findByEmail(email);
    }

    public JogadorModel salvar(JogadorModel jogador) {
        return jogadorRepository.save(jogador);
    }
    
    public JogadorModel registrarJogador(String apelido, String email, String rawPassword, String role) {
        if (jogadorRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Jogador com este email já existe");
        }

        String encodedPassword = passwordEncoder.encode(rawPassword);

        JogadorModel jogador = new JogadorModel();
        jogador.setApelido(apelido);
        jogador.setEmail(email);
        jogador.setSenha(encodedPassword);
        jogador.setRole(role == null ? "ROLE_USER" : role);

        return jogadorRepository.save(jogador);
    }

    public void deletar(Integer id) {
        jogadorRepository.deleteById(id);
    }
}
