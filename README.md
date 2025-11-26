# Simplified ERP (Marketplace Inventory)

Um sistema de **Mini ERP**, desenvolvido com **Java 21** e **Spring Boot 3**.

Este projeto adota princípios de **Clean Architecture** para desacoplar as regras de negócio da infraestrutura, garantindo testabilidade e manutenção facilitada.

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.5.5
* **Segurança:** Spring Security + JWT (JSON Web Token)
* **Banco de Dados:** H2 Database (Em memória para desenvolvimento/testes)
* **Persistência:** Spring Data JPA
* **Mapeamento:** MapStruct
* **Ferramentas:** Maven, Lombok, JUnit

## 🏗️ Arquitetura e Design Patterns

O projeto está estruturado seguindo camadas bem definidas:

1.  **Domain:** Contém as entidades, exceções e regras de negócio puras.
    * *Design Pattern:* **Strategy Pattern** (`StockStrategyFactory`) utilizado para definir dinamicamente o cálculo de movimentação de estoque (ex: VENDA subtrai, COMPRA soma).
2.  **Application:** Contém os Casos de Uso (`UseCase`) e interfaces de Gateway.
3.  **Infrastructure:** Implementação dos Gateways, Repositórios JPA, configurações de Segurança e Mappers.
    * *Design Pattern:* **Decorator Pattern** (`TransactionalCreateProduct`, etc.) utilizado para gerenciar transações sem poluir a lógica de negócio.
4.  **Presentation:** Controladores REST (Controllers) e DTOs (Records).

## 🛠️ Configuração e Instalação

### Pré-requisitos
* Java JDK 21
* Maven

### Executando a aplicação

1.  Clone o repositório:
    ```bash
    git clone [https://github.com/seu-usuario/simplified-erp.git](https://github.com/seu-usuario/simplified-erp.git)
    ```
2.  Navegue até a pasta do projeto e compile:
    ```bash
    cd simplified-erp
    ./mvnw clean install
    ```
3.  Execute a aplicação:
    ```bash
    ./mvnw spring-boot:run
    ```

A aplicação iniciará na porta padrão `8080`.

## 🔐 Acesso Inicial e Banco de Dados

O sistema utiliza o banco H2 em memória. Ao iniciar, um usuário administrador padrão é criado se não existir:

* **Usuário:** `admin`
* **Senha:** `Admin@123`

> **Console H2:** `http://localhost:8080/h2-console`
> * **JDBC URL:** `jdbc:h2:mem:testdb`
> * **User:** `sa`
> * **Password:** *(deixe em branco)*

## 📡 Endpoints da API

A autenticação é feita via **Bearer Token**. Você deve obter o token no endpoint de login e enviá-lo no header `Authorization` das demais requisições.

### Autenticação (`/api/v1/auth`)

| Método | Rota | Descrição | Payload Exemplo |
| --- | --- | --- | --- |
| POST | `/login` | Realiza login e retorna o Token JWT | `{ "username": "admin", "password": "..." }` |

### Produtos (`/api/v1/products`)
*Requer Role: HOST ou ADMIN*

| Método | Rota | Descrição |
| --- | --- | --- |
| POST | `/` | Cria um novo produto |
| PUT | `/{id}` | Atualiza um produto existente |
| GET | `/` | Lista todos os produtos |
| GET | `/{id}` | Busca produto por ID |
| DELETE | `/{id}` | Remove um produto (*Apenas ADMIN*) |

**Exemplo de JSON (Produto):**
```json
{
  "sku": "PROD-001",
  "name": "Notebook Gamer",
  "description": "Notebook i7, 16GB RAM",
  "price": 4500.00,
  "stock": 10,
  "currency": "BRL"
}
```

# Usuários (`/api/v1/users`)
**Requer Role:** `ADMIN`

| Método | Rota | Descrição |
|--------|-------|-----------|
| **POST** | `/` | Cria um novo usuário |
| **PUT** | `/{id}` | Atualiza um usuário existente |
| **GET** | `/` | Lista todos os usuários |
| **GET** | `/{id}` | Busca usuário por ID |
| **GET** | `/username/{username}` | Busca usuário pelo username |
| **DELETE** | `/{id}` | Remove um usuário |

## Exemplo de JSON (Usuário)
```json
{
  "name": "João Silva",
  "username": "joao.silva",
  "password": "Password123!",
  "role": "HOST"
}
```

**Roles disponíveis:** `ADMIN`, `HOST`

---

# Movimentação de Estoque (`/api/v1/movements`)
**Requer Role:** `HOST` ou `ADMIN`

Este endpoint é responsável por registrar as entradas e saídas, atualizando automaticamente a quantidade de estoque do produto vinculado.

| Método | Rota | Descrição |
|--------|------|-----------|
| **POST** | `/` | Registra uma nova movimentação |

## Tipos de Movimento Suportados (`movementType`)

### Subtraem estoque:
- `SALE`
- `EXIT`

### Adicionam estoque:
- `ENTRY`
- `PURCHASE`
- `RETURN`

### Configuráveis pela estratégia:
- `ADJUSTMENT`
- `TRANSFER`
- `PRODUCTION`
- `CONSUMPTION`
- `OTHER`

## Exemplo de JSON (Movimento)
```json
{
  "productId": 1,
  "quantity": 50,
  "movementType": "ENTRY"
}
```

---

# Testes

O projeto utiliza **JUnit 5** e **Spring Boot Test** para garantir a qualidade do código.

## Executando os testes
```bash
./mvnw test
```

## Cobertura de Testes

Os testes cobrem cenários importantes, como:

- Validações de domínio (preço negativo, campos obrigatórios)
- Carregamento do contexto da aplicação
- Regras críticas de negócio

---

# Simplified ERP  
Desenvolvido por Danilo Alves




