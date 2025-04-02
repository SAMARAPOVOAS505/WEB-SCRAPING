
package com.samara.webscraping.controller;

import com.samara.webScraping.Model.Operadora;
import com.samara.webScraping.repository.OperadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operadoras")
public class OperadoraController {

    @Autowired
    private OperadoraRepository operadoraRepository;

    @GetMapping("/buscar")
    public List<Operadora> buscarOperadora(@RequestParam String termo) {
        return operadoraRepository.findByNomeOperadoraContainingIgnoreCase(termo);
    }
}

