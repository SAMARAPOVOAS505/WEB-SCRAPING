package com.samara.webScraping.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Data  // Lombok gera automaticamente getters, setters, toString, equals e hashCode
public class Operadora {

    @Id
    private Long id;
    private String codigoOperadora;
    private String nomeOperadora;
    private String cnpj;
    private String dataCadastro;
    private String endereco;
    private String cidade;
    private String uf;
    private String telefone;
    private String email;

}
