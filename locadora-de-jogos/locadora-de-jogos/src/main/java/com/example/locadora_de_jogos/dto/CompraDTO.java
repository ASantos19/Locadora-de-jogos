package com.example.locadora_de_jogos.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompraDTO extends LicencaDTO {
    private LocalDate dataCompra;
    private Integer notaFiscal;
}
