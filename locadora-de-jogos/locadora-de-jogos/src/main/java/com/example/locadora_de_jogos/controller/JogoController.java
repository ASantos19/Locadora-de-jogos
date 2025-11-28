package com.example.locadora_de_jogos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.locadora_de_jogos.model.JogoModel;
import com.example.locadora_de_jogos.service.JogoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jogos")
@RequiredArgsConstructor
public class JogoController {

    private final JogoService jogoService;

    @GetMapping
    public ResponseEntity<List<JogoModel>> listar() {
        return ResponseEntity.ok(jogoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoModel> buscarPorId(@PathVariable Integer id) {
        Optional<JogoModel> jogo = jogoService.buscarPorId(id);
        return jogo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<JogoModel> criar(@RequestBody JogoModel jogo) {
        return ResponseEntity.ok(jogoService.salvar(jogo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogoModel> atualizar(
            @PathVariable Integer id,
            @RequestBody JogoModel jogo) {

        Optional<JogoModel> existente = jogoService.buscarPorId(id);

        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        jogo.setId(id);
        return ResponseEntity.ok(jogoService.salvar(jogo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        jogoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
