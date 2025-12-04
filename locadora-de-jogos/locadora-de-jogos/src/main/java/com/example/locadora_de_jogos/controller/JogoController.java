package com.example.locadora_de_jogos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.locadora_de_jogos.dto.JogoDTO;
import com.example.locadora_de_jogos.dto.NovoJogoDTO;
import com.example.locadora_de_jogos.model.JogoModel;
import com.example.locadora_de_jogos.service.JogoService;



@RestController
@RequestMapping("/jogos")
public class JogoController {
    @Autowired
    private JogoService jogoService;
    
    @GetMapping
    public ResponseEntity<List<JogoModel>> listar() {
    List<JogoModel> jogos = jogoService.listar();
    return ResponseEntity.ok(jogos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<JogoModel> buscarPorId(@PathVariable Integer id) {
        return jogoService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        
    }

    @GetMapping("/resumo/{id}")
    public ResponseEntity<JogoDTO> buscarJogoPorId(@PathVariable Integer id) {
    return jogoService.buscarPorId(id)
        .map(jogo -> {
            JogoDTO dto = new JogoDTO();
            dto.setId(jogo.getId());
            dto.setNomeJogo(jogo.getNomeJogo());
            dto.setGeneroJogo(jogo.getGeneroJogo());
            dto.setPlataforma(jogo.getPlataforma());
            dto.setPrecoAluguel(jogo.getPrecoAluguel());
            dto.setPrecoCompra(jogo.getPrecoCompra());
            return dto; 
        })
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
            

    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')" )
    public ResponseEntity<JogoModel> criar(@RequestBody JogoModel jogo) {
        JogoModel novoJogo = jogoService.salvar(jogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJogo);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JogoModel> atualizar(@PathVariable Integer id, @RequestBody JogoModel jogo) {
        if (!jogoService.buscarPorId(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        jogo.setId(id);
        JogoModel jogoAtualizado = jogoService.salvar(jogo);
        return ResponseEntity.ok(jogoAtualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!jogoService.buscarPorId(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();    
        }
        jogoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/novo")
    public ResponseEntity<JogoModel> adicionarJogo(@RequestBody NovoJogoDTO novoJogoDTO) {
        JogoModel novoJogo = new JogoModel();
        novoJogo.setNomeJogo(novoJogoDTO.getNome());
        novoJogo.setGeneroJogo(novoJogoDTO.getGeneroJogo());
        novoJogo.setPlataforma(novoJogoDTO.getPlataforma());
        novoJogo.setPrecoAluguel(novoJogoDTO.getPrecoAluguel());
        novoJogo.setPrecoCompra(novoJogoDTO.getPrecoCompra());
        JogoModel jogoSalvo = jogoService.salvar(novoJogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogoSalvo);
    }
}
