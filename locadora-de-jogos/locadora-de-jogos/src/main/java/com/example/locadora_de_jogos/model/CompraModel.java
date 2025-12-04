package com.example.locadora_de_jogos.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COMPRA")
public class CompraModel extends LicencaModel {

    @Column(name = "data_compra")
    private LocalDate dataCompra;

    @Column(name = "nota_fiscal")
    private Integer notaFiscal;

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Integer getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(Integer notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
}