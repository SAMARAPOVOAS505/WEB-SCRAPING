package com.samara.webScraping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samara.webScraping.Model.Procedimento;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, String> {
    
}
