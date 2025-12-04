package com.example.locadora_de_jogos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "jogador")
@JsonIgnoreProperties("licencas")
public class JogadorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String apelido;
    
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(nullable = false, length = 200)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    
    @Column(nullable = false, length = 30)
    private String role; // ROLE_USER ou ROLE_ADMIN



    @OneToMany(mappedBy = "jogador")
    private List<LicencaModel> licencas;

    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public List<LicencaModel> getLicencas() { return licencas; }
    public void setLicencas(List<LicencaModel> licencas) { this.licencas = licencas; }
}
