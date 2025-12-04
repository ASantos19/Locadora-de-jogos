package com.example.locadora_de_jogos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.locadora_de_jogos.dto.AluguelDTO;
import com.example.locadora_de_jogos.model.AluguelModel;
import com.example.locadora_de_jogos.model.JogoModel;
import com.example.locadora_de_jogos.model.enums.EstadoLicenca;
import com.example.locadora_de_jogos.repository.AluguelRepository;
import com.example.locadora_de_jogos.service.JogoService;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public List<AluguelModel> listar() {
        return aluguelRepository.findAll();
    }

    public Optional<AluguelModel> buscarPorId(Integer id) {
        return aluguelRepository.findById(id);
    }

    public AluguelModel salvar(AluguelModel aluguel){
        return aluguelRepository.save(aluguel);
    }

    public void deletar(Integer id) {
        aluguelRepository.deleteById(id);
    }

}
