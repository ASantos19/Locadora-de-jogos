package com.example.locadora_de_jogos.model;

import java.util.List;
import com.example.locadora_de_jogos.model.enums.GeneroJogo;
import com.example.locadora_de_jogos.model.enums.Plataforma;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties("licencas")
public class JogoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_jogo")
    private String nomeJogo;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero_jogo")
    private GeneroJogo generoJogo;

    @Enumerated(EnumType.STRING)
    @Column(name = "plataforma")
    private Plataforma plataforma;

    @Column(name = "preco_compra")
    private Float precoCompra;

    @Column(name = "preco_aluguel")
    private Float precoAluguel;

    @OneToMany(mappedBy = "jogo")
    private List<LicencaModel> licencas;

    // getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeJogo() { return nomeJogo; }
    public void setNomeJogo(String nomeJogo) { this.nomeJogo = nomeJogo; }

    public GeneroJogo getGeneroJogo() { return generoJogo; }
    public void setGeneroJogo(GeneroJogo generoJogo) { this.generoJogo = generoJogo; }

    public Plataforma getPlataforma() { return plataforma; }
    public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }

    public Float getPrecoCompra() { return precoCompra; }
    public void setPrecoCompra(Float precoCompra) { this.precoCompra = precoCompra; }

    public Float getPrecoAluguel() { return precoAluguel; }
    public void setPrecoAluguel(Float precoAluguel) { this.precoAluguel = precoAluguel; }

    public List<LicencaModel> getLicencas() { return licencas; }
    public void setLicencas(List<LicencaModel> licencas) { this.licencas = licencas; }
}

