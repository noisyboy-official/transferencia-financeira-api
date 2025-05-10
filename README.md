# 💰 Transferência Financeira API

API REST desenvolvida com Spring Boot para gerenciamento de transferências financeiras, permitindo agendamento, cálculo automático de taxas e consultas detalhadas das operações realizadas e futuras.

## 🚀 Funcionalidades

- **Agendamento de Transferências:** Permite agendar transferências financeiras para datas futuras.
- **Cálculo Automático de Taxas:** Aplica automaticamente taxas diferenciadas conforme o período agendado.
- **Consulta de Transferências:** Retorna listas separadas para transferências realizadas e agendadas.
- **Validações Avançadas:** Garante integridade dos dados e consistência das operações financeiras.

## 🛠️ Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Hibernate](https://hibernate.org/)
- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [JUnit 5](https://junit.org/junit5/) e [Mockito](https://site.mockito.org/) para testes automatizados

## 🔧 Requisitos

- Java 11 ou superior
- Maven
- Banco de dados compatível com JPA (por exemplo, MySQL, PostgreSQL)

## ▶️ Executando o Projeto

### Clone o repositório

```bash
git clone https://github.com/noisyboy-official/transferencia-financeira-api.git
```

### Navegue até o diretório do projeto

```bash
cd transferencia-financeira-api
```

### Instale as dependências

```bash
mvn install
```

### Configure o Banco de Dados

Configure as informações do banco no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/transferencia_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### Execute o projeto

```bash
mvn spring-boot:run
```

O serviço estará disponível em: `http://localhost:8080`

## 📚 Documentação da API (Swagger)

A documentação detalhada da API pode ser acessada pelo Swagger:

```
http://localhost:8080/swagger-ui.html
```

## ✅ Testes Automatizados

Para executar os testes:

```bash
mvn test
```

## 📂 Estrutura do Projeto

```
transferencia-financeira-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── avaliacao.transferencia/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── avaliacao.transferencia/
│               └── service/
├── pom.xml
└── README.md
```

## 📜 Licença

Este projeto está sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

- **Gabriel Aguiar** - [noisyboy-official](https://github.com/noisyboy-official)

---

🌟 **[Repositório Original do Projeto](https://github.com/noisyboy-official/transferencia-financeira-api)** 🌟
