package com.samara.webScraping.Service;

import java.io.FileReader;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.samara.webScraping.Model.Operadora;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperadoraService {
    private List<Operadora> operadoras;

    public OperadoraService() {
        carregarCSV();
    }

    private void carregarCSV() {
        try (Reader reader = new FileReader("src/main/resources/operadoras_ativas.csv")) {
            CsvToBean<Operadora> csvToBean = new CsvToBeanBuilder<Operadora>(reader)
                    .withType(Operadora.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            operadoras = csvToBean.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Operadora> buscarOperadoras(String nome) {
        return operadoras.stream()
                .filter(o -> o.getRazaoSocial().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }
}
