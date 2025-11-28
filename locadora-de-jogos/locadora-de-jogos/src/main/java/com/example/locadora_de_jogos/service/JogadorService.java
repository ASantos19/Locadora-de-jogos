package com.example.locadora_de_jogos.service;

import com.example.locadora_de_jogos.model.JogadorModel;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.locadora_de_jogos.repository.JogadorRepository;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    public List<JogadorModel> listar() {
        return jogadorRepository.findAll();
    }

    public Optional<JogadorModel> buscarPorId(Integer id) {
        return jogadorRepository.findById(id);
    }

    public JogadorModel salvar(JogadorModel jogador) {
        return jogadorRepository.save(jogador);
    }

    public void deletar(Integer id) {
        jogadorRepository.deleteById(id);
    }
}
