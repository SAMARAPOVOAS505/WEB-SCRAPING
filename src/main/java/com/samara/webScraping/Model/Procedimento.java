package com.samara.webScraping.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Procedimento {
    
    @Id
    private String codigo;

    private String descricao;
    private String tipo;

}
