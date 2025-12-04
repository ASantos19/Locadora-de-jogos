package com.example.locadora_de_jogos.dto;

import com.example.locadora_de_jogos.model.enums.GeneroJogo;
import com.example.locadora_de_jogos.model.enums.Plataforma;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JogoDTO {
    private Integer id;
    private String nomeJogo;
    private GeneroJogo generoJogo;
    private Plataforma plataforma;
    private Float precoCompra;
    private Float precoAluguel;
}

