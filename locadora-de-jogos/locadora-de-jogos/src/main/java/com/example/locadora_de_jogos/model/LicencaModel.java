package com.example.locadora_de_jogos.model;


import com.example.locadora_de_jogos.model.enums.EstadoLicenca;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_licenca")
public abstract class LicencaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private EstadoLicenca estadoLic;

    @ManyToOne
    @JoinColumn(name = "jogador_id")
    @JsonIgnoreProperties("licencas")
    private JogadorModel jogador;
     

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    @JsonIgnoreProperties("licencas")
    private JogoModel jogo;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public EstadoLicenca getEstadoLic() {
        return estadoLic;
    }
    public void setEstadoLic(EstadoLicenca estadoLic) {
        this.estadoLic = estadoLic;
    }
    
    public JogadorModel getJogador() {
        return jogador;
    }
    public void setJogador(JogadorModel jogador) {
        this.jogador = jogador;
    }

    public JogoModel getJogo() {
        return jogo;
    }
    public void setJogo(JogoModel jogo) {
        this.jogo = jogo;
    }
}
