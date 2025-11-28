package com.example.locadora_de_jogos.dto;

import com.example.locadora_de_jogos.model.enums.EstadoLicenca;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LicencaDTO {
    private Integer id;
    private EstadoLicenca estadoLic;
    private Integer jogadorId;
    private Integer jogoId;
}
