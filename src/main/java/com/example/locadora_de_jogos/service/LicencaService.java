package com.example.locadora_de_jogos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.locadora_de_jogos.model.LicencaModel;
import com.example.locadora_de_jogos.repository.LicencaRepository;

@Service
public class LicencaService {

    @Autowired
    private LicencaRepository licencaRepository;

    public List<LicencaModel> listar() {
        return licencaRepository.findAll();
    }

    public Optional<LicencaModel> buscarPorId(Integer id) {
        return licencaRepository.findById(id);
    }

    public LicencaModel salvar(LicencaModel licenca) {
        return licencaRepository.save(licenca);
    }

    public void deletar(Integer id) {
        licencaRepository.deleteById(id);
    }
}
