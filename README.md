# GestaoDeEstoque
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](https://github.com/Luis-Parente/GestaoDeEstoque/blob/main/LICENSE)

API REST desenvolvida em Java + Spring Boot para gerenciamento de estoque, com controle de acesso baseado em perfis de login e autenticação via JWT.

## 📋 Requisitos
- Java 21 ou superior
- Git

## 📦 Tecnologias Utilizadas
- Java 21
- Spring Boot 3.5
- Spring Data JPA
- H2 Database (ambiente de desenvolvimento)
- Spring Security + JWT (autenticação)
- Lombok (redução de boilerplate)
- Swagger/OpenAPI (documentação da API)
- Maven Wrapper
- Postman (testes)

## 🛠️ Como executar

### 1. Clone o repositório:
````bash
git clone https://github.com/Luis-Parente/GestaoDeEstoque.git
cd GestaoDeEstoque
````
### 2. Construa o projeto
````bash
./mvnw clean install
````
### 3. Rode a aplicação
````bash
./mvnw spring-boot:run
````

## 🔗 Endpoints e Testes
- API Base URL: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui/index.html

## 🔍 Testando a API
Você pode testar de duas maneiras:
### 1. Swagger UI
Acesse:
http://localhost:8080/swagger-ui/index.html

### 2. Postman
O repositório inclui dois arquivos prontos para importar:
- Arquivo: EspaçoLCosmeticos.postman_collection.json
- Ambiente: EspaçoLCosmeticos.postman_environment.json

Como importar:
- Abra o Postman
- Vá em File > Import
- Selecione os arquivos .json do repositório
- Você pode então enviar requisições diretamente usando os endpoints e dados pré-configurados.

## ✅ Funcionalidades principais
- Login de usuario
- CRUD completo para usuario
- CRUD completo para produto
- Endpoint para atualizar desconto dos produtos filtrados
- Endpoint para atualizar quanitade em estoque dos produtos
