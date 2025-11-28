package com.example.locadora_de_jogos.repository;

import com.example.locadora_de_jogos.model.JogoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<JogoModel, Integer> {
}
