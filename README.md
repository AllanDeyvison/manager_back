# Manager Back

## Visão Geral

Este projeto é uma API RESTful desenvolvida em Java com Spring Boot para gerenciar usuários e mensagens, incluindo autenticação JWT, integração com PostgreSQL, documentação Swagger/OpenAPI. O sistema foi pensado para ser modular, seguro e facilmente extensível.

---

## Estrutura do Projeto

```
manager_back/
├── src/
│   ├── main/
│   │   ├── java/com/manager/
│   │   │   ├── configuration/   # Configurações globais (Swagger, WebClient, Utils)
│   │   │   ├── controllers/     # Controllers REST (User, Message)
│   │   │   ├── models/          # Entidades JPA (User, Message, PastPasswords, UserLogin, UserType)
│   │   │   ├── repositories/    # Interfaces JPA para acesso ao banco
│   │   │   ├── security/        # Segurança (JWT, filtros, configs)
│   │   │   └── services/        # Regras de negócio (UserService, MessageService)
│   │   └── resources/
│   │       └── application.yaml # Configurações do Spring Boot
│   └── test/
│       └── java/com/manager/    # Testes automatizados
├── pom.xml                      # Gerenciamento de dependências Maven
└── README.md                    # Documentação do projeto
```

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
  - Spring Data JPA
  - Spring Security
  - Spring Web & WebFlux
- **PostgreSQL** (banco de dados relacional)
- **JWT (JSON Web Token)** para autenticação
- **Lombok** para reduzir boilerplate
- **Swagger/OpenAPI** para documentação automática
- **RabbitMQ** (opcional, para mensageria)
- **Gson** para manipulação de JSON
- **Maven** para build e dependências

---

## Módulos e Funcionalidades

- **User**
  - Cadastro, login, atualização, deleção
  - Alteração de senha e tipo de usuário (USER/ADMIN)
  - Autenticação JWT
- **Message**
  - Envio, listagem, busca e deleção de mensagens
  - Associação de mensagens a usuários
- **Segurança**
  - Filtro JWT para proteger rotas
  - Configuração de CORS e CSRF
- **Documentação**
  - Swagger UI disponível em `/swagger-ui.html`
- **Mensageria (RabbitMQ)**
  - Código preparado para integração, mas comentado por padrão

---

## Como Rodar o Projeto

### 1. Pré-requisitos

- Java 17+
- Maven 3.9+
- PostgreSQL rodando e configurado
- (Opcional) RabbitMQ rodando

### 2. Configuração do Banco de Dados

No arquivo [`src/main/resources/application.yaml`](src/main/resources/application.yaml), configure as variáveis de ambiente:

```
POSTGRESDATABASE=seubanco
POSTGRESHOST=localhost
POSTGRESPORT=5432
POSTGRESUSER=seuusuario
POSTGRESPASSWORD=suasenha
```

Você pode definir essas variáveis no ambiente do sistema ou em um arquivo `.env` (caso use ferramentas como Docker ou IDEs que suportam).

### 3. Instalação das Dependências

No terminal, execute:

```sh
./mvnw clean install
```
ou no Windows:
```cmd
mvnw.cmd clean install
```

### 4. Rodando a Aplicação

```sh
./mvnw spring-boot:run
```
ou no Windows:
```cmd
mvnw.cmd spring-boot:run
```

A API estará disponível em: [http://localhost:8090](http://localhost:8090)

### 5. Acessando a Documentação

Acesse [http://localhost:8090/swagger-ui.html](http://localhost:8090/swagger-ui.html) para visualizar e testar os endpoints.

---

## Como Funciona

- **Autenticação:** Usuários se cadastram e fazem login. O login retorna um JWT, que deve ser enviado no header `Authorization` para acessar rotas protegidas.
- **Usuários:** Podem ser criados, atualizados, deletados e ter o tipo alterado (USER/ADMIN).
- **Mensagens:** Usuários autenticados podem enviar, listar e deletar mensagens.
- **Segurança:** Todas as rotas (exceto login, cadastro e update de senha) exigem autenticação JWT.
- **Documentação:** Swagger/OpenAPI gera documentação interativa dos endpoints.
- **Mensageria:** O projeto está preparado para enviar mensagens para uma fila RabbitMQ (código comentado).

---

## Desenvolvimento

O projeto foi desenvolvido seguindo boas práticas de arquitetura REST, separando responsabilidades em camadas (Controller, Service, Repository, Model). O uso de Spring Security e JWT garante segurança nas operações. O Swagger facilita o entendimento e testes da API.

---

## Melhorias Futuras

- Implementar histórico de senhas (já existe entidade PastPasswords)
- Adicionar testes automatizados para todos os serviços
- Melhorar tratamento de erros e mensagens de retorno

---

## Contato

Desenvolvido por [Allan Bia Nick](https://github.com/AllanDeyvison/manager_back)  
Dúvidas: ainteligencia@gmail.com
