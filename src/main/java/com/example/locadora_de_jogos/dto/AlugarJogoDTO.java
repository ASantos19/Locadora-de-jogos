package com.example.locadora_de_jogos.dto;

import com.example.locadora_de_jogos.model.enums.EstadoLicenca;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AlugarJogoDTO {

    private String nomeJogo;
    private Float precoAluguel;
    private LocalDate dataAtivacao;
    private LocalDate dataExpiracao;
    private EstadoLicenca estado;
}

