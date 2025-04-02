package com.samara.webScraping;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

public class DadosRolProcedimentos {
    private static final String PDF_FILE_PATH = "anexos_ans/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf";
    private static final String CSV_FILE_PATH = "Rol_Procedimentos.csv";
    private static final String ZIP_FILE_PATH = "Teste_Samara.zip";

    private static final Map<String, String> ABBREVIATIONS = new HashMap<>();

    static {
        ABBREVIATIONS.put("OD", "Odontologia");
        ABBREVIATIONS.put("AMB", "Ambulatório");
        // Adicione mais abreviações conforme necessário
    }

    public static void main(String[] args) {
        try {
            // 2.1 Extrair os dados do PDF
            List<String[]> dadosTabela = extractDataFromPDF(PDF_FILE_PATH);

            // 2.2 Salvar os dados no formato CSV
            saveDataToCSV(dadosTabela, CSV_FILE_PATH);

            // 2.3 Compactar o CSV em um arquivo ZIP
            zipCSV(CSV_FILE_PATH, ZIP_FILE_PATH);

            System.out.println("Processo concluído com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2.1 Extração dos dados do PDF
    private static List<String[]> extractDataFromPDF(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);

            // Extrair o texto de todas as páginas
            String text = stripper.getText(document);
            String[] lines = text.split("\n");

            // Processar linha por linha (exemplo simplificado)
            for (String line : lines) {
                if (line.contains("OD") || line.contains("AMB")) {
                    String[] columns = line.split("\\s+");  // Assume que as colunas estão separadas por espaços
                    // Substituir abreviações por descrições completas
                    for (int i = 0; i < columns.length; i++) {
                        if (ABBREVIATIONS.containsKey(columns[i])) {
                            columns[i] = ABBREVIATIONS.get(columns[i]);
                        }
                    }
                    data.add(columns);
                }
            }
        }
        
        return data;
    }

    // 2.2 Salvar os dados extraídos em CSV
    private static void saveDataToCSV(List<String[]> data, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Escrever as linhas no CSV
            writer.writeAll(data);
        }
    }

    // 2.3 Compactar o CSV em um arquivo ZIP
    private static void zipCSV(String csvFilePath, String zipFilePath) throws IOException {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath));
             FileInputStream fis = new FileInputStream(csvFilePath)) {

            ZipEntry zipEntry = new ZipEntry(new File(csvFilePath).getName());
            zipOut.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zipOut.write(buffer, 0, length);
            }

            zipOut.closeEntry();
        }
    }
}

