package com.example.locadora_de_jogos.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ALUGUEL")
public class AluguelModel extends LicencaModel {

    @Column(name = "data_ativacao")
    private LocalDate dataAtivacao;

    @Column(name = "data_expiracao")
    private LocalDate dataExpiracao;

    public LocalDate getDataAtivacao() {
        return dataAtivacao;
    }

    public void setDataAtivacao(LocalDate dataAtivacao) {
        this.dataAtivacao = dataAtivacao;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

}