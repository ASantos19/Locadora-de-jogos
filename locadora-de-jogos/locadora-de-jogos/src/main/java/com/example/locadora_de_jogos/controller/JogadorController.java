package com.example.locadora_de_jogos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.locadora_de_jogos.model.JogadorModel;
import com.example.locadora_de_jogos.service.JogadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jogadores")
@RequiredArgsConstructor
public class JogadorController {

    private final JogadorService jogadorService;

    @GetMapping
    public ResponseEntity<List<JogadorModel>> listar() {
        return ResponseEntity.ok(jogadorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorModel> buscarPorId(@PathVariable Integer id) {
        Optional<JogadorModel> jogador = jogadorService.buscarPorId(id);
        return jogador.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<JogadorModel> criar(@RequestBody JogadorModel jogador) {
        return ResponseEntity.ok(jogadorService.salvar(jogador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorModel> atualizar(
            @PathVariable Integer id,
            @RequestBody JogadorModel jogador) {

        Optional<JogadorModel> existente = jogadorService.buscarPorId(id);

        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        jogador.setId(id);
        return ResponseEntity.ok(jogadorService.salvar(jogador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        jogadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
