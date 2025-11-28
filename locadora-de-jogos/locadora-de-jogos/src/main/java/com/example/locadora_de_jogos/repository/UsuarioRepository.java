package com.example.locadora_de_jogos.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.locadora_de_jogos.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    Optional<UsuarioModel> findByUsername(String username);
    boolean existsByUsername(String username);
}

