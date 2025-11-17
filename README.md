# BankApplication-Challenge2

Um desafio para a criação de contas bancárias, clientes e registros de transações utilizando **Spring Boot**, com PostgreSQL, validação de saldo e limites, integração com Docker e deploy no Render.

---

## Observações sobre o deploy

- Este projeto está hospedado na versão gratuita do Render.
- Instâncias gratuitas “dormem” quando não há atividade, portanto:
  - A **primeira requisição** após um período de inatividade pode demorar ou retornar temporariamente erro 502.
  - Aguarde de 1 a 2 minutos após a primeira requisição e tente novamente para ter sucesso.

---

## Swagger

A documentação interativa via Swagger está disponível online em:  
[BankApplication Swagger](https://bankapplication-challenge2.onrender.com/swagger-ui/index.html)

---

## Tecnologias utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- PostgreSQL  
- Maven  
- Docker / Podman  
- Swagger (OpenAPI)  
- Lombok  

---

## Funcionalidades

- Cadastro de clientes e contas (corrente e poupança)  
- Consulta de saldo e limite de transferências  
- Saque, depósito e transferências entre contas  
- Validação de saldo e limites  
- Registro de transações  
- Documentação interativa via Swagger  
- Deploy via Docker e Render  

---

### Pré-requisitos

- Java 17  
- Maven  
- Docker ou Podman  
- PostgreSQL  

## Endpoints

### Customer
- **GET** `/customers` – Lista todos os clientes  
- **GET** `/customers/{id}` – Busca cliente por ID  
- **POST** `/customers` – Cria novo cliente  

### Account
- **GET** `/accounts` – Lista todas as contas  
- **GET** `/accounts/{id}` – Busca conta por ID  
- **PUT** `/savings/{accountNumber}` – Atualiza conta poupança  
- **PUT** `/checking/{accountNumber}` – Atualiza conta corrente  
- **POST** `/accounts/checking` – Cria conta corrente  
- **POST** `/accounts/savings` – Cria conta poupança  

### Transaction
- **POST** `/transactions/deposit` – Depositar valor  
- **POST** `/transactions/withdraw` – Sacar valor  
- **POST** `/transactions/transfer` – Transferir valor entre contas  
- **GET** `/transactions/{accountNumber}` – Lista todas as transações de uma conta pelo número dela  

