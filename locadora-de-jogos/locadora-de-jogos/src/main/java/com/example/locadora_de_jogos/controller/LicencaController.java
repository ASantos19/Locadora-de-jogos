package com.example.locadora_de_jogos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<LicencaModel>> listar() {
        return ResponseEntity.ok(licencaService.listar());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<LicencaModel> buscarPorId(@PathVariable Integer id) {
        Optional<LicencaModel> licenca = licencaService.buscarPorId(id);
        return licenca.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LicencaModel> criar(@RequestBody LicencaModel licenca) {
        return ResponseEntity.ok(licencaService.salvar(licenca));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LicencaModel> atualizar( @PathVariable Integer id,@RequestBody LicencaModel licenca) {
        if (!licencaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } 
        licenca.setId(id);
        LicencaModel licencaAtualizada = licencaService.salvar(licenca);
        return ResponseEntity.ok(licencaAtualizada);
            
        
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!licencaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        licencaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

