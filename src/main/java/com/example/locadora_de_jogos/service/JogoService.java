package com.example.locadora_de_jogos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.locadora_de_jogos.model.JogoModel;
import com.example.locadora_de_jogos.repository.JogoRepository;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    public List<JogoModel> listar() {
        return jogoRepository.findAll();
    }

    public Optional<JogoModel> buscarPorId(Integer id) {
        return jogoRepository.findById(id);
    }

    public JogoModel salvar(JogoModel jogo) {
        return jogoRepository.save(jogo);
    }

    public void deletar(Integer id) {
        jogoRepository.deleteById(id);
    }
}
