package com.samara.webScraping;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.zip.*;

public class WebScrapingAns {
    private static final String FILE_URL_1 = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf";
    private static final String FILE_URL_2 = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025.pdf";
    private static final String DOWNLOAD_FOLDER = "anexos_ans";
    private static final String ZIP_FILENAME = "anexos_ans.zip";

    public static void main(String[] args) {
        try {
            // Criar diretório para os arquivos
            Files.createDirectories(Paths.get(DOWNLOAD_FOLDER));

            // Baixar os dois arquivos
            downloadFile(FILE_URL_1);
            downloadFile(FILE_URL_2);

            // Criar o arquivo ZIP com os anexos
            zipFiles();

            System.out.println("Processo concluído! Arquivos ZIP criados com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void downloadFile(String fileUrl) throws IOException {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        Path filePath = Paths.get(DOWNLOAD_FOLDER, fileName);

        try (InputStream in = new URL(fileUrl).openStream()) {
            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Baixado: " + fileName);
        } catch (IOException e) {
            System.err.println("Erro ao baixar: " + fileUrl);
        }
    }

    private static void zipFiles() throws IOException {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(ZIP_FILENAME))) {
            File folder = new File(DOWNLOAD_FOLDER);
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        ZipEntry zipEntry = new ZipEntry(file.getName());
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
        }
        System.out.println("Arquivo ZIP criado: " + ZIP_FILENAME);
    }
}

