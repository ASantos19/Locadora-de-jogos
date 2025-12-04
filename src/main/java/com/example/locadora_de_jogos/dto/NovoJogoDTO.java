package com.example.locadora_de_jogos.dto;

import com.example.locadora_de_jogos.model.enums.GeneroJogo;
import com.example.locadora_de_jogos.model.enums.Plataforma;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NovoJogoDTO {
    private String nome;
    private GeneroJogo generoJogo;
    private Plataforma plataforma;
    private Float precoAluguel;
    private Float precoCompra;

}
