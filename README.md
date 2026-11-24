# itaujava

API REST em Spring Boot que resolve o desafio técnico de back-end do Itaú: recebe transações, guarda em memória e devolve estatísticas das transações dos últimos 60 segundos.

## Tecnologias

- Java 17
- Spring Boot 4 (Web MVC, Validation)
- springdoc-openapi (Swagger UI)
- Lombok
- Maven (wrapper incluso)

## Como rodar

```bash
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`. A documentação interativa fica em `http://localhost:8080/swagger-ui.html`.

Para rodar os testes:

```bash
./mvnw test
```

## Endpoints

| Método | Rota | Descrição | Respostas |
|--------|------|-----------|-----------|
| `POST` | `/transacao` | Registra uma transação | `201` criada · `422` regra violada · `400` JSON inválido |
| `DELETE` | `/transacao` | Apaga todas as transações | `200` |
| `GET` | `/estatistica` | Estatísticas da janela configurada | `200` |

### POST /transacao

```json
{
  "valor": 150.75,
  "dataHora": "2024-01-15T10:30:00-03:00"
}
```

Regras (respondem `422` sem corpo quando violadas):

- `valor` e `dataHora` são obrigatórios;
- `valor` deve ser maior que zero;
- `dataHora` não pode estar no futuro.

### GET /estatistica

```json
{
  "count": 3,
  "avg": 200.5,
  "max": 300.0,
  "min": 100.5,
  "sum": 601.5
}
```

Sem transações na janela, todos os campos vêm `0`.

## Configuração

A janela das estatísticas é definida em `src/main/resources/application.yml`:

```yaml
estatistica:
  segundos: 60
```

## Estrutura

```
src/main/java/EduardoDesplanches/itaujava
├── transacao/    controller, service, repository em memória, DTO e mapper
├── Estatistica/  controller, modelo de resposta e propriedades da janela
├── Docs/         interfaces com as anotações OpenAPI dos controllers
├── Config/       configuração do OpenAPI
└── GlobalExceptionHandler.java
```
