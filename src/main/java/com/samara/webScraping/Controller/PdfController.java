package com.samara.webScraping.Controller;

import com.samara.webScraping.Model.Procedimento;
import com.samara.webScraping.Repository.ProcedimentoRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    @PostMapping("/extract")
    public ResponseEntity<String> extractPdfData(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arquivo PDF não enviado.");
        }

        String csvPath = "Rol_de_Procedimentos.csv";
        String zipPath = "Teste_Samara.zip";

        try {
            // Salvar o PDF temporariamente
            Path tempPdf = Files.createTempFile("tempPdf", ".pdf");
            file.transferTo(tempPdf);

            // Extraindo texto do PDF
            PDDocument document = PDDocument.load(tempPdf.toFile());
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            document.close();

            // Processando texto para extrair tabelas
            String[] lines = text.split("\n");
            try (CSVWriter writer = new CSVWriter(new FileWriter(csvPath))) {
                for (String line : lines) {
                    String[] columns = line.split("\\s{2,}"); // Assume colunas separadas por múltiplos espaços
                    if (columns.length > 1) {
                        Procedimento procedimento = new Procedimento();
                        procedimento.setCodigo(columns[0]);
                        procedimento.setDescricao(columns[1]);
                        procedimento.setTipo(replaceAbbreviations(columns[2]));

                        // Salvar no banco de dados
                        procedimentoRepository.save(procedimento);

                        // Escrever CSV
                        writer.writeNext(columns);
                    }
                }
            }

            // Compactando CSV para ZIP
            try (FileOutputStream fos = new FileOutputStream(zipPath);
                 ZipOutputStream zos = new ZipOutputStream(fos);
                 FileInputStream fis = new FileInputStream(csvPath)) {
                ZipEntry zipEntry = new ZipEntry(csvPath);
                zos.putNextEntry(zipEntry);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) >= 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
            }

            return ResponseEntity.ok("Processo concluído. Arquivo ZIP gerado: " + zipPath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o PDF: " + e.getMessage());
        }
    }

    private static String replaceAbbreviations(String text) {
        return text.replace("OD", "Odontológico")
                   .replace("AMB", "Ambulatorial");
    }
}
