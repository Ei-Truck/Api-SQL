# 🚚 API de Gestão de Viagens e Motoristas

Esta API permite *gerenciar viagens, motoristas, caminhões e usuários do sistema*, fornecendo **visões consolidadas**, **filtros personalizados** e **tratativas específicas para cada viagem**.  

O sistema foi projetado para **integrar dados operacionais** e **simplificar o acompanhamento logístico** de forma centralizada e escalável.  

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Função |
|-------------|--------|
| *Java 21+* | Linguagem principal |
| *Spring Boot* | Framework backend |
| *Spring Data JPA* | Mapeamento e persistência no banco de dados |
| *PostgreSQL* | Banco de dados relacional |
| *Maven* | Gerenciador de dependências |
| *Docker* | Containerização da aplicação |
| *.env* | Configuração externa e segura de variáveis de ambiente |

---

## 📦 Estrutura do Projeto

```
Api-SQL/
├── .github/                     
│   ├── workflows/               -> CI/CD
├── src/                           
│   ├── main/                      
│   │   ├── java/com/apisql/ApiSQL/
│   │   │   ├── controller/      -> Controladores REST
│   │   │   │   └── health/      -> Endpoint health
│   │   │   ├── service/         -> Regras de negócio
│   │   │   │   └── view/        -> Regras de negócio (views)
│   │   │   ├── repository/      -> Acesso ao banco via JPA
│   │   │   │   └── view/        -> Acesso ao banco via EntityManager (views)
│   │   │   ├── model/           -> Entidades do sistema
│   │   │   │   └── enums/       -> Valores Constantes
│   │   │   ├── openapi/         -> Documentação do Swagger
│   │   │   ├── security/        -> Segurança e autenticação da aplicação
│   │   │   ├── config/          -> Configuração de Serviços
│   │   │   ├── dto/             -> Objetos de transferência de dados
│   │   │   │   └── view/        -> Objetos de resposta (views)
│   │   │   └── exception/       -> Gerenciamento de exceções
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
├── pom.xml                      -> Dependências Maven
├── Dockerfile                   -> Configuração Docker
└── README.md
```

---

## 🏃 Como Executar o Projeto

### 1️⃣ Criar o arquivo `.env`
Crie um arquivo `.env` na raiz do projeto com as credenciais do banco de dados:

```env
DB_HOST=localhost
DB_PORT=5432
DB_NAME=api_sql
DB_USER=postgres
DB_PASSWORD=senha123
DB_SSLMODE=disable
```

---

### 2️⃣ Criar a imagem Docker

```bash
docker build -t api-sql .
```

---

### 3️⃣ Rodar o container

```bash
docker run -p 8080:8080 --env-file .env api-sql
```

---

## 📖 Documentação Swagger

Após subir o container ou rodar o projeto com `mvn spring-boot:run`, acesse:

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 📡 Como Usar

### Exemplo de requisição — **Consultar visão básica das viagens**

```http
GET /viagens/visao-basica
Authorization: Bearer <seu_token_jwt>
```

### Exemplo de resposta

```json
[
  {
    "idViagem": 11,
    "idMotorista": 5,
    "nomeMotorista": "Carlos Almeida",
    "placaCaminhao": "ABC-1234",
    "tratativa": "Entrega reprogramada",
    "status": "Em andamento"
  }
]
```

---

## 🔧 Variáveis de Ambiente

Crie um arquivo `.env` com as variáveis:

```env
DB_HOST=
DB_PORT=
DB_NAME=
DB_USER=
DB_PASSWORD=
DB_SSLMODE=
```

💡 *Você pode se basear no arquivo `.env.example` incluído no projeto.*

---

## 🧩 Endpoints Principais

| Método | Rota | Descrição |
|--------|------|-----------|
| `GET` | `/viagens/visao-basica` | Retorna visão consolidada das viagens |
| `GET` | `/usuarios/logado` | Retorna informações do usuário autenticado |
| `POST` | `/viagens` | Cria nova viagem |
| `PUT` | `/viagens/{id}` | Atualiza dados da viagem |
| `DELETE` | `/viagens/{id}` | Remove uma viagem existente |

---

## 📝 Observações

- **As queries SQL utilizam visões (views)** e **joins otimizados** para evitar duplicidades e acelerar o carregamento.  
- **A API suporta autenticação e autorização por cargos e unidades.**  
- **Certifique-se de configurar corretamente as variáveis de ambiente** para conexão ao banco PostgreSQL.  
- Para ambientes de produção, recomenda-se **habilitar SSL e usar secrets seguros** em vez de `.env` direto.
