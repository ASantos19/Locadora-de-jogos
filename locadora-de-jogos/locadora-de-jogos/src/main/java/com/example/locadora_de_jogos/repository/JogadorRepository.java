package com.example.locadora_de_jogos.repository;

import com.example.locadora_de_jogos.model.JogadorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<JogadorModel, Integer> {
}
