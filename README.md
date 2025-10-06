# Consult Credit Project

![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)
![Gradle](https://img.shields.io/badge/Build-Gradle-green.svg)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue.svg)
![Kafka](https://img.shields.io/badge/Messaging-Apache%20Kafka-black.svg)
![Docker](https://img.shields.io/badge/Containerization-Docker-blue.svg)
![Swagger](https://img.shields.io/badge/API%20Docs-Swagger-85CC14.svg)

---

## 📄 Sobre o Projeto

Este projeto visa atender o desafio para a vaga de Sênior, a proposta visa a criação de uma API RESTful utilizando Spring Boot para a consulta de créditos constituídos. A API fornecerá informações essenciais como número do crédito constituído, número da NFS-e, data da constituição do crédito, valor do ISSQN, tipo do crédito e outros atributos.

O desafio contem uma segunda parte, que seria uma aplicação frontend em **Angular**, que será melhor descrito em um segundo repositório disponível nesse [link aqui](https://github.com/JoelMarSDS/consult-credit-app).

A aplicação permite consultar informações de crédito, e integra-se com Kafka para registrar eventos de processamento e erro de forma desacoplada.

### Projetos adicionais:

* Frontend: [consult-credit-app](https://github.com/JoelMarSDS/consult-credit-app)
* Docker Compose: [work](https://github.com/JoelMarSDS/work)
---
## 🔗 Links Uteis

* Swagger: http://localhost:8080/api/swagger-ui/index.html
* Kafka UI: http://localhost:9000/

---
## 🚀 Tecnologias Utilizadas

O backend é construído com as seguintes tecnologias:

* **Java 21**
* **Spring Boot 3.5.3**
* **Spring Data JPA / Hibernate**
* **Apache Kafka**
* **PostgreSQL**
* **Gradle**
* **Swagger/OpenAPI**

**Infraestrutura e Orquestração:**
* **Docker**: Conteinerização dos serviços de backend, banco de dados e Kafka.
* **Kafka UI**: Interface web para monitoramento e gerenciamento do cluster Kafka (acessível via Docker Compose).
* **Zookeeper**: Coordenador do cluster Kafka (gerenciado pelo Docker Compose).

---

## 📦 Estrutura do Projeto

O backend é organizado em um monorepo Gradle com os seguintes módulos:

* `consult-credit-api`: Módulo principal da API REST, responsável pelos endpoints HTTP e integração com a camada de domínio.
* `consult-credit-domain`: Módulo de domínio, contendo as entidades de dados (modelos), repositórios (JPA) e a lógica de negócio principal do serviço de consulta de crédito.
* `kafka-logging`: Módulo dedicado à integração com Apache Kafka, contendo produtores e consumidores para o sistema de logging de eventos de crédito.
---

## ⚙️ Configuração Local e Execução

Para rodar o backend do projeto em sua máquina local, você precisará ter o **PostgreSQL** e um ambiente **Kafka** (incluindo Zookeeper) em execução e acessíveis.

---
> #### 💡 Dica: Antes de executar os passos a seguir aqui vai uma dica!
> 
> **Executar ambiente completo com Docker Compose(Opional)**:
> 
> Caso queira apenas testar o funcionamento do projeto completo, com PostgrSQL, Zookeeper, Kafka, Kafka-UI, backend e frontend, basta ir no Repositório do Docker Compose ([link aqui](https://github.com/JoelMarSDS/work)) e seguir as instruções descritas no README.

---

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/JoelMarSDS/consult-credit.git
    ```

2.  **Configure o Banco de Dados:**
    * Certifique-se de ter uma instância do **PostgreSQL** rodando.
    * Crie um banco de dados chamado `public` (ou o nome configurado em `application.yaml`).
    * As credenciais do banco de dados (usuário `postgres`, senha `admin`) devem ser definidas em seu ambiente ou no arquivo `application.yaml`.

3.  **Configure o Kafka:**
    * Certifique-se de ter um cluster **Kafka** (com Zookeeper) rodando e acessível na porta `9092` (ou a porta configurada em `application.yaml`).

4.  **Ajuste o `application.yaml` do Spring Boot:**
    No `consult-credit-api/src/main/resources/application.yaml`, verifique e ajuste as configurações de conexão com o PostgreSQL e o Kafka para que apontem para suas instâncias locais:

    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://${HOST_DB:localhost}:5432/public
        username: ${USER_ADMIN:postgres}
        password: ${PASS_ADMIN:admin}
      kafka:
        bootstrap-servers: ${SPRING_KAFKA_BOOTSTRAP_SERVERS:localhost}:9092
    ```
    * Você pode definir as variáveis de ambiente `HOST_DB`, `USER_ADMIN`, `PASS_ADMIN` e `SPRING_KAFKA_BOOTSTRAP_SERVERS` em seu sistema operacional ou diretamente nos valores do YAML.

5.  **Compile e Execute o Projeto:**
    Na raiz do projeto, execute o comando Gradle para construir e rodar a aplicação Spring Boot:
    ```bash
    ./gradlew bootRun
    ```
    Este comando irá:
    * Compilar os módulos do backend.
    * Iniciar a aplicação Spring Boot.
6. Inserts:
   Após a aplicação pronta, será executada uma rotina para inserir os dados para teste. abaixo está o script executado.

   ```SQL
    INSERT INTO credito (
        numero_credito,
        numero_nfse,
        data_constituicao,
        valor_issqn,
        tipo_credito,
        simples_nacional,
        aliquota,
        valor_faturado,
        valor_deducao,
        base_calculo)
    VALUES
        ('123456', '7891011', '2024-02-25', 1500.75, 'ISSQN', true, 5.0, 30000.00, 5000.00, 25000.00),
        ('789012', '7891011', '2024-02-26', 1200.50, 'ISSQN', false, 4.5, 25000.00, 4000.00, 21000.00),
        ('654321', '1122334', '2024-01-15', 800.50, 'Outros', true, 3.5, 20000.00, 3000.00, 17000.00);
   ```
---

## 🏃 Acessando a API

Após a inicialização bem-sucedida da aplicação Spring Boot, você pode acessar a API:

* **Backend API (Swagger UI):** `http://localhost:8080/api/swagger-ui.html`
    * Os endpoints da API estarão acessíveis em `http://localhost:8080/api`.

---

## 💡 Observações Importantes

* **Variáveis de Ambiente:** As credenciais do banco de dados e as configurações do Kafka podem ser sobrescritas por variáveis de ambiente do sistema operacional.
* **CORS:** A configuração CORS (`WebConfiguration.java`) no módulo `consult-credit-api` permite que outras origens (como um frontend ou ferramentas de teste) acessem a API.

---

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para contribuir com dicas de melhorias.

---
