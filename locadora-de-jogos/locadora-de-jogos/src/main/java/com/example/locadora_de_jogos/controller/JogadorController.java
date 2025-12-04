package com.example.locadora_de_jogos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.locadora_de_jogos.dto.JogadorDTO;
import com.example.locadora_de_jogos.model.JogadorModel;
import com.example.locadora_de_jogos.service.JogadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jogadores")
@RequiredArgsConstructor
public class JogadorController {

    private final JogadorService jogadorService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<JogadorModel>> listar() {
        List<JogadorModel> jogadores = jogadorService.listar();
        if (jogadores.isEmpty()) {
            return ResponseEntity.noContent().build();  
        }
        return ResponseEntity.ok(jogadores);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JogadorModel> buscarPorId(@PathVariable Integer id) {
        return jogadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/jogador/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<JogadorDTO> buscarJogadorPorId(@PathVariable Integer id) {
        return jogadorService.buscarPorId(id)
                .map(jogador -> {
                    JogadorDTO dto = new JogadorDTO();
                    dto.setId(jogador.getId());
                    dto.setApelido(jogador.getApelido());
                    dto.setEmail(jogador.getEmail());
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JogadorModel> criar(@RequestBody JogadorModel jogador) {
        JogadorModel novoJogador = jogadorService.salvar(jogador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJogador);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JogadorModel> atualizar( @PathVariable Integer id, @RequestBody JogadorModel jogador) {
        if (!jogadorService.buscarPorId(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        jogador.setId(id);
        JogadorModel jogadorAtualizado = jogadorService.salvar(jogador);
        return ResponseEntity.ok(jogadorAtualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!jogadorService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        jogadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
