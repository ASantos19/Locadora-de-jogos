package com.example.locadora_de_jogos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.locadora_de_jogos.model.LicencaModel;
import com.example.locadora_de_jogos.service.LicencaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/licencas")
@RequiredArgsConstructor
public class LicencaController {

    private final LicencaService licencaService;

    @GetMapping
    public ResponseEntity<List<LicencaModel>> listar() {
        return ResponseEntity.ok(licencaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LicencaModel> buscarPorId(@PathVariable Integer id) {
        Optional<LicencaModel> licenca = licencaService.buscarPorId(id);
        return licenca.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LicencaModel> criar(@RequestBody LicencaModel licenca) {
        return ResponseEntity.ok(licencaService.salvar(licenca));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LicencaModel> atualizar(
            @PathVariable Integer id,
            @RequestBody LicencaModel licenca) {

        Optional<LicencaModel> existente = licencaService.buscarPorId(id);

        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        licenca.setId(id);
        return ResponseEntity.ok(licencaService.salvar(licenca));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        licencaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

