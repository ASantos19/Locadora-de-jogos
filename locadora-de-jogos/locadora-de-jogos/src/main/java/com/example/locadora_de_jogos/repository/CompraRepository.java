package com.example.locadora_de_jogos.repository;

import com.example.locadora_de_jogos.model.CompraModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<CompraModel, Integer> {
}
