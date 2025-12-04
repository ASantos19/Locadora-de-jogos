package com.example.locadora_de_jogos.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.locadora_de_jogos.dto.CompraDTO;
import com.example.locadora_de_jogos.model.CompraModel;
import com.example.locadora_de_jogos.model.JogadorModel;
import com.example.locadora_de_jogos.model.JogoModel;
import com.example.locadora_de_jogos.model.enums.EstadoLicenca;
import com.example.locadora_de_jogos.service.CompraService;
import com.example.locadora_de_jogos.service.JogadorService;
import com.example.locadora_de_jogos.service.JogoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;
    private final JogoService jogoService;
    private final JogadorService jogadorService;

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

    @GetMapping("/{id}/detalhado")
    public ResponseEntity<CompraDTO> buscarDetalhado(@PathVariable Integer id) {
        return compraService.buscarPorId(id)
            .map(compra -> {
                CompraDTO dto = new CompraDTO();
                dto.setId(compra.getId());
                dto.setEstadoLic(compra.getEstadoLic());
                dto.setDataCompra(compra.getDataCompra());
                dto.setNotaFiscal(compra.getNotaFiscal());

                if (compra.getJogador() != null)
                    dto.setJogadorId(compra.getJogador().getId());

                if (compra.getJogo() != null)
                    dto.setJogoId(compra.getJogo().getId());

                return ResponseEntity.ok(dto);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/comprar")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<CompraDTO> comprar(@RequestBody CompraDTO dto) {

            JogoModel jogo = jogoService.buscarPorId(dto.getJogoId())
                    .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

            JogadorModel jogador = jogadorService.buscarPorId(dto.getJogadorId())
                    .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

            CompraModel compra = new CompraModel();
            compra.setJogo(jogo);
            compra.setJogador(jogador);
            compra.setEstadoLic(EstadoLicenca.VENDIDO); 
            compra.setDataCompra(
                dto.getDataCompra() != null ? dto.getDataCompra() : LocalDate.now()
            );
            compra.setNotaFiscal(dto.getNotaFiscal());

            CompraModel salva = compraService.salvar(compra);

            
            CompraDTO resposta = new CompraDTO();
            resposta.setId(salva.getId());
            resposta.setEstadoLic(salva.getEstadoLic());
            resposta.setDataCompra(salva.getDataCompra());
            resposta.setNotaFiscal(salva.getNotaFiscal());
            resposta.setJogoId(salva.getJogo().getId());
            resposta.setJogadorId(salva.getJogador().getId());

            return ResponseEntity.ok(resposta);
        }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CompraModel> atualizar(@PathVariable Integer id, @RequestBody CompraModel compra) {
        if (!compraService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } 
        compra.setId(id);
        CompraModel compraAtualizada = compraService.salvar(compra);
        return ResponseEntity.ok(compraAtualizada);
        
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if(!compraService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        compraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

