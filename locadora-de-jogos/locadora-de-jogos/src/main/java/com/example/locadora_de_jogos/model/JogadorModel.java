package com.example.locadora_de_jogos.model;

import java.sql.Date;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class JogadorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String apelido;
    private String email;
    private String senha;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @OneToMany(mappedBy = "jogador")
    private List<LicencaModel> licencas;

    // getters e setters (mesmos que você já tinha)
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }

    public List<LicencaModel> getLicencas() { return licencas; }
    public void setLicencas(List<LicencaModel> licencas) { this.licencas = licencas; }
}
