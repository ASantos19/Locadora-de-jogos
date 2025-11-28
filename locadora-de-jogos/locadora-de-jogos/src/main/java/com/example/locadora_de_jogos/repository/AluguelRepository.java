package com.example.locadora_de_jogos.repository;

import com.example.locadora_de_jogos.model.AluguelModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AluguelRepository extends JpaRepository<AluguelModel, Integer> {
}
