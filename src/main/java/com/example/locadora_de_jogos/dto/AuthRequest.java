package com.example.locadora_de_jogos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequest {
    private String email; // Usado como username para autenticação
    private String password;
    private String apelido; // Opcional, usado apenas no registro
}

