package com.example.locadora_de_jogos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {
    private String username;
    private String password;
    private String role; // opcional: "ROLE_USER" por padrão se nulo
}
