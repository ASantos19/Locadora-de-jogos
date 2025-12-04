package com.example.locadora_de_jogos.repository;

import com.example.locadora_de_jogos.model.JogadorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JogadorRepository extends JpaRepository<JogadorModel, Integer> {
    Optional<JogadorModel> findByEmail(String email);
    boolean existsByEmail(String email);
}
