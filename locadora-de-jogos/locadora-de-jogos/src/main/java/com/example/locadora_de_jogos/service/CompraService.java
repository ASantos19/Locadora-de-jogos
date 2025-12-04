package com.example.locadora_de_jogos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.locadora_de_jogos.model.CompraModel;
import com.example.locadora_de_jogos.repository.CompraRepository;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;
    

    public List<CompraModel> listar() {
        return compraRepository.findAll();
    }

    public Optional<CompraModel> buscarPorId(Integer id) {
        return compraRepository.findById(id);
    }

    public CompraModel salvar(CompraModel compra){
        return compraRepository.save(compra);
    }

    public void deletar(Integer id) {
        compraRepository.deleteById(id);
    }
}
