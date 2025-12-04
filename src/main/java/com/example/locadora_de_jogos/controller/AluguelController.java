package com.example.locadora_de_jogos.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.locadora_de_jogos.dto.AluguelDTO;
import com.example.locadora_de_jogos.model.AluguelModel;
import com.example.locadora_de_jogos.model.JogadorModel;
import com.example.locadora_de_jogos.model.JogoModel;
import com.example.locadora_de_jogos.model.enums.EstadoLicenca;
import com.example.locadora_de_jogos.service.AluguelService;
import com.example.locadora_de_jogos.service.JogadorService;
import com.example.locadora_de_jogos.service.JogoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/alugueis")
@RequiredArgsConstructor
public class AluguelController {

    private final AluguelService aluguelService;
    private final JogoService jogoService;
    private final JogadorService jogadorService;

    
    @GetMapping
    public ResponseEntity<List<AluguelModel>> listar() {
        return ResponseEntity.ok(aluguelService.listar());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<AluguelModel> buscarPorId(@PathVariable Integer id) {
        return aluguelService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    
    @GetMapping("/detalhado/{id}")
    public ResponseEntity<AluguelDTO> buscarDetalhado(@PathVariable Integer id) {
        return aluguelService.buscarPorId(id)
                .map(aluguel -> {
                    AluguelDTO dto = new AluguelDTO();
                    dto.setId(aluguel.getId());
                    dto.setEstadoLic(aluguel.getEstadoLic());
                    dto.setDataAtivacao(aluguel.getDataAtivacao());
                    dto.setDataExpiracao(aluguel.getDataExpiracao());

                    if (aluguel.getJogador() != null) dto.setJogadorId(aluguel.getJogador().getId());
                    if (aluguel.getJogo() != null) dto.setJogoId(aluguel.getJogo().getId());

                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping("/alugar")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<AluguelDTO> alugar(@RequestBody AluguelDTO dto) {

        JogoModel jogo = jogoService.buscarPorId(dto.getJogoId())
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        JogadorModel jogador = jogadorService.buscarPorId(dto.getJogadorId())
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        AluguelModel aluguel = new AluguelModel();
        aluguel.setJogo(jogo);
        aluguel.setJogador(jogador);
        aluguel.setEstadoLic(EstadoLicenca.ALUGADO);
        aluguel.setDataAtivacao(dto.getDataAtivacao() != null ? dto.getDataAtivacao() : LocalDate.now());
        aluguel.setDataExpiracao(dto.getDataExpiracao());

        AluguelModel salvo = aluguelService.salvar(aluguel);

        AluguelDTO resposta = new AluguelDTO();
        resposta.setId(salvo.getId());
        resposta.setEstadoLic(salvo.getEstadoLic());
        resposta.setDataAtivacao(salvo.getDataAtivacao());
        resposta.setDataExpiracao(salvo.getDataExpiracao());
        resposta.setJogoId(salvo.getJogo().getId());
        resposta.setJogadorId(salvo.getJogador().getId());

        return ResponseEntity.ok(resposta);
    }

    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AluguelModel> atualizar(@PathVariable Integer id, @RequestBody AluguelModel aluguel) {
        if (!aluguelService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        aluguel.setId(id);
        AluguelModel aluguelAtualizado = aluguelService.salvar(aluguel);
        return ResponseEntity.ok(aluguelAtualizado);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (aluguelService.buscarPorId(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        aluguelService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
