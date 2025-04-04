# Web Scraping e Transformação de Dados

Este projeto foi desenvolvido com o objetivo de demonstrar um conjunto de habilidades em **Web Scraping**, **Transformação de Dados** e **Manipulação de Arquivos**, utilizando tecnologias como Java, Spring Boot, bibliotecas de manipulação de arquivos, e integração com banco de dados.

## Funcionalidades

O projeto é dividido em diferentes tarefas com foco em três principais etapas:

### 1. **Web Scraping**
A primeira parte do projeto envolve a extração de dois arquivos PDF de um site público da ANS (Agência Nacional de Saúde Suplementar), contendo documentos de procedimentos e eventos em saúde. O sistema realiza:

- Acessa o site para obter os documentos PDF.
- Realiza o download dos anexos diretamente para o sistema.
- Compacta os arquivos em um único arquivo ZIP para facilitar o armazenamento e o envio.

### 2. **Transformação de Dados**
Após o download dos arquivos, o projeto processa os dados contidos no PDF, convertendo as informações para um formato estruturado. As principais tarefas incluem:

- Extração de dados da tabela **Rol de Procedimentos e Eventos em Saúde** do PDF.
- Salvamento dos dados em um arquivo **CSV**.
- Compactação do CSV em um arquivo ZIP.
- Substituição de abreviações de colunas por descrições completas, conforme a legenda do documento.

### 3. **Banco de Dados**
A terceira parte do projeto envolve a criação de um banco de dados para armazenar os dados extraídos e transformados, permitindo consultas analíticas. As funcionalidades incluem:

- Estruturação das tabelas no banco de dados (MySQL ou PostgreSQL).
- Importação dos dados para o banco a partir dos arquivos CSV.
- Execução de consultas analíticas para responder perguntas específicas sobre as operadoras de planos de saúde, como as 10 operadoras com maiores despesas em "EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MÉDICO HOSPITALAR" no último trimestre e no último ano.

### 4. **Integração com API**
O projeto também inclui uma interface web desenvolvida em **Vue.js** que interage com um servidor em **Spring Boot** para realizar buscas textuais nos dados das operadoras e retornar os registros mais relevantes.

### 5. **Objetivo**
Este projeto demonstra uma aplicação prática de técnicas de scraping de dados, manipulação de arquivos e integração com banco de dados. Ele visa ajudar na organização e análise de informações provenientes de documentos públicos.

---

## Tecnologias Utilizadas

- **Java 8** com **Spring Boot**
- **Vue.js** para a interface web
- **Jsoup** para Web Scraping
- **Apache Commons Compress** para manipulação de arquivos ZIP
- **MySQL/PostgreSQL** para armazenamento de dados
- **Postman** para a realização de testes de API

## Como Rodar o Projeto

Para rodar o projeto localmente, siga as etapas abaixo:

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/SAMARAPOVOAS505/WEB-SCRAPING.git
   cd WEB-SCRAPING
   ```

2. **Instale as dependências do projeto:**

   Se você estiver rodando o projeto Spring Boot, certifique-se de ter o **Maven** instalado. Execute o seguinte comando para rodar a aplicação:

   ```bash
   mvn spring-boot:run
   ```

3. **Interface Web (Vue.js):**

   Caso deseje rodar a interface Vue.js, entre na pasta da interface e execute:

   ```bash
   npm install
   npm run serve
   ```

4. **Banco de Dados:**

   - Crie as tabelas necessárias no banco de dados MySQL/PostgreSQL usando as queries fornecidas.
   - Importe os arquivos CSV para as tabelas do banco de dados.

---

## Licença

Este projeto é de uso livre para fins educacionais e não comerciais. 

---

**Se tiver alguma dúvida ou sugestão, fique à vontade para abrir uma issue ou entrar em contato!**
