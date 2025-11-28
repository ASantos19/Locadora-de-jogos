package com.example.locadora_de_jogos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.locadora_de_jogos.model.AluguelModel;
import com.example.locadora_de_jogos.service.AluguelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/alugueis")
@RequiredArgsConstructor
public class AluguelController {

    private final AluguelService aluguelService;

    @GetMapping
    public ResponseEntity<List<AluguelModel>> listar() {
        return ResponseEntity.ok(aluguelService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AluguelModel> buscarPorId(@PathVariable Integer id) {
        Optional<AluguelModel> aluguel = aluguelService.buscarPorId(id);
        return aluguel.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AluguelModel> criar(@RequestBody AluguelModel aluguel) {
        return ResponseEntity.ok(aluguelService.salvar(aluguel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AluguelModel> atualizar(
            @PathVariable Integer id,
            @RequestBody AluguelModel aluguel) {

        Optional<AluguelModel> existente = aluguelService.buscarPorId(id);

        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        aluguel.setId(id);
        return ResponseEntity.ok(aluguelService.salvar(aluguel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        aluguelService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

