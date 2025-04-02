package com.samara.webScraping.repository;

import com.samara.webScraping.Model.Operadora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperadoraRepository extends JpaRepository<Operadora, Long> {
    List<Operadora> findByNomeOperadoraContainingIgnoreCase(String nomeOperadora);
}

