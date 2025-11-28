package com.example.locadora_de_jogos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.locadora_de_jogos.model.CompraModel;
import com.example.locadora_de_jogos.service.CompraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @GetMapping
    public ResponseEntity<List<CompraModel>> listar() {
        return ResponseEntity.ok(compraService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraModel> buscarPorId(@PathVariable Integer id) {
        Optional<CompraModel> compra = compraService.buscarPorId(id);
        return compra.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CompraModel> criar(@RequestBody CompraModel compra) {
        return ResponseEntity.ok(compraService.salvar(compra));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraModel> atualizar(
            @PathVariable Integer id,
            @RequestBody CompraModel compra) {

        Optional<CompraModel> existente = compraService.buscarPorId(id);

        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        compra.setId(id);  
        return ResponseEntity.ok(compraService.salvar(compra));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        compraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

