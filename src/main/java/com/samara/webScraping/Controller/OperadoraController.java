package com.samara.webScraping.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samara.webScraping.Model.Operadora;
import com.samara.webScraping.Service.OperadoraService;

@RestController
@RequestMapping("/operadora")
@CrossOrigin(origins = "*")
public class OperadoraController {
     private final OperadoraService operadoraService;

    public OperadoraController(OperadoraService operadoraService) {
        this.operadoraService = operadoraService;
    }

    @GetMapping("/buscar")
    public List<Operadora> buscar(@RequestParam String nome) {
        return operadoraService.buscarOperadoras(nome);
    }
    
}
