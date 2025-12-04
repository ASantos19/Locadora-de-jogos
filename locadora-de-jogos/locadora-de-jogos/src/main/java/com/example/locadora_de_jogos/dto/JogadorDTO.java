package com.example.locadora_de_jogos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class JogadorDTO {
    private Integer id;
    private String apelido;
    private String email;
    private String senha;
    private String role;
}
