# 🛡️ API de Gerenciamento de Inventário Seguro

[![Java](https://img.shields.io/badge/Java-21-red.svg?logo=openjdk&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen.svg?logo=spring)](https://spring.io/projects/spring-boot)
[![Security](https://img.shields.io/badge/Spring%20Security-JWT-blue.svg?logo=spring-security)]()
[![Database](https://img.shields.io/badge/Database-H2-gray.svg?logo=h2)]()
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Este projeto é um template robusto de **Microsserviço Spring Boot** para gerenciamento de **inventário de produtos e usuários**. Ele demonstra a aplicação de padrões avançados de desenvolvimento, focando em **Clean Architecture** e **Segurança com JWT**.

---

## ✨ Destaques do Projeto

O foco principal é o domínio de padrões de software:

1.  **🏗️ Clean Architecture:** Separação explícita em camadas (Domain, Application, Infrastructure, Presentation).
2.  **🔒 Segurança Robusta:** Configuração completa do **Spring Security** com **JWT** e regras de validação complexas para senhas.
3.  **🔄 CRUD Completo:** Gerenciamento total de Produtos e Usuários.
4.  **📚 Tecnologias Modernas:** Utilização de Java 21, Spring Boot 3 e **MapStruct** para mapeamento de DTOs.

---

## 🏗️ Arquitetura (Clean Architecture)

| Camada | Responsabilidade Principal | Exemplos |
| :--- | :--- | :--- |
| **Domain** | Entidades de Negócio e Validação. | `ProductDomain`, `UserValidator`. |
| **Application** | Orquestração (Casos de Uso) e Contratos (`Gateways`). | `ProductCreationUseCase`, `UserRepositoryGateway`. |
| **Infrastructure** | Implementação de Persistência, Segurança e Mappers. | `ProductRepositoryJPA`, `JwtService`. |
| **Presentation** | Exposição da API REST, DTOs e Tratamento de Exceções. | `ProductController`, `GlobalExceptionHandler`. |

---

## ✅ Funcionalidades (Endpoints Chave)

Todas as rotas, exceto `POST /api/v1/users` e o login, exigem autenticação via JWT no cabeçalho `Authorization: Bearer <token>`.

### 🔑 Autenticação
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/v1/auth/login` | Autentica e retorna o **JWT**. |

### 📦 Produtos (Requer Autenticação)
| Método | Endpoint | Uso |
| :--- | :--- | :--- |
| `GET` | `/api/v1/products` | Lista todos. |
| `GET` | `/api/v1/products/{id}` | Busca por ID. |
| `POST` | `/api/v1/products` | Cria um novo produto. |
| `PUT` | `/api/v1/products/{id}` | Atualiza o produto. |
| `DELETE` | `/api/v1/products/{id}` | Remove o produto. |

### 👤 Usuários
| Método | Endpoint | Requisito de Role |
| :--- | :--- | :--- |
| `POST` | `/api/v1/users` | N/A (Rota de Cadastro). |
| `GET` | `/api/v1/users` | **'ADMIN'** |
| `PUT` | `/api/v1/users/{id}` | **'ADMIN'** |
| `DELETE` | `/api/v1/users/{id}` | **'ADMIN'** |

---

## 🛠️ Tecnologias Utilizadas

| Categoria | Tecnologia |
| :--- | :--- |
| **Linguagem** | Java 21 |
| **Framework** | Spring Boot 3.5.5 |
| **Segurança** | Spring Security e JJWT |
| **Persistência** | Spring Data JPA |
| **Banco de Dados** | H2 Database (In-Memory) |
| **Mapeamento** | **MapStruct** |
| **Build** | Maven |

---

## ⚙️ Como Executar

O projeto utiliza o **H2 Database** em memória, sendo ideal para desenvolvimento e testes rápidos.

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/daniloalves1902/inventory-management-api.git](https://github.com/daniloalves1902/inventory-management-api.git)
    ```

2.  **Acesse o diretório:**
    ```bash
    cd inventory-management-api
    ```

3.  **Execute a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```

### Endereços

| Serviço | Endereço |
| :--- | :--- |
| **API Principal** | 👉 `http://localhost:8080` |
| **Console do H2** | 👉 `http://localhost:8080/h2-console` |

### 🔑 Credenciais Padrão

Um usuário **ADMIN** é criado na inicialização (`DataInitializer.java`) para testes:

| Campo | Valor |
| :--- | :--- |
| **Username** | `admin` |
| **Password** | `Admin@123` |

---

## 📝 Exemplos de Corpo de Requisição

### 📦 Produtos (`POST /api/v1/products`):

```json
{
  "sku": "EX-PROD-001",
  "name": "Produto de Exemplo",
  "description": "Descrição do produto de exemplo.",
  "price": 149.90,
  "stock": 15
}
```
👤 Usuários (`POST /api/v1/users`):

A senha deve atender às seguintes Regras de Validação:
- Mínimo 8 caracteres.
- Pelo menos 1 caractere especial.
- Pelo menos 1 número.
- Pelo menos 1 letra maiúscula.

```json

{
  "name": "Nome do Usuário",
  "username": "usuarioteste",
  "password": "Password@123",
  "role": "HOST"
}
