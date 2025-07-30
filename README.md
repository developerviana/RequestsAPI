# 🧾 Sistema de Pedidos com Spring Boot, RabbitMQ e PostgreSQL

## 📝 Resumo

Este projeto implementa uma solução baseada em microserviços para processar, armazenar e disponibilizar informações de pedidos recebidos via RabbitMQ. Desenvolvido em Java (Spring Boot), utiliza RabbitMQ como broker de mensagens e PostgreSQL como banco de dados relacional, todos orquestrados com Docker Compose. A aplicação expõe uma API REST para consultar o valor total de pedidos, a quantidade de pedidos por cliente e listar todos os pedidos de um cliente.

---

## 📌 Contexto

Empresas frequentemente precisam integrar sistemas de pedidos de forma assíncrona, desacoplada e escalável. O uso do RabbitMQ garante resiliência e escalabilidade, permitindo o processamento de mensagens conforme a disponibilidade dos recursos.

---

## 🎯 Objetivos

- Desenvolver uma aplicação backend em Java com Spring Boot.
- Modelar e implementar uma base de dados relacional.
- Processar dados recebidos por uma fila RabbitMQ.
- Persistir pedidos e itens consumidos.
- Expor uma API REST com as seguintes funcionalidades:
	- Consultar valor total de um pedido.
	- Consultar quantidade de pedidos por cliente.
	- Listar pedidos de um cliente.

---

## 📐 Modelo da Mensagem (JSON)

```json
{
	"codigoPedido": 1001,
	"codigoCliente": 1,
	"itens": [
		{ "produto": "lápis", "quantidade": 100, "preco": 1.10 },
		{ "produto": "caderno", "quantidade": 10, "preco": 1.00 }
	]
}
```

---

## 🧱 Estrutura do Projeto

- **Consumer (RabbitMQ):** Escuta a fila e processa os pedidos.
- **Persistência (Spring Data + PostgreSQL):** Salva os dados no banco.
- **API REST (Spring Boot):** Disponibiliza endpoints para consulta.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia     | Finalidade                          |
| -------------- | ----------------------------------- |
| Java 17        | Linguagem principal                 |
| Spring Boot    | Framework para API REST e injeção   |
| Spring Data    | ORM simplificado com JPA            |
| RabbitMQ       | Broker de mensageria assíncrono     |
| PostgreSQL     | Banco de dados relacional           |
| Docker         | Containerização dos serviços        |
| Docker Compose | Orquestração dos containers         |

---

## ⚙️ Endpoints da API

| Método | Endpoint                      | Descrição                                 |
| ------ | ----------------------------- | ----------------------------------------- |
| GET    | `/pedidos/total/{codigo}`     | Retorna o valor total de um pedido        |
| GET    | `/clientes/{id}/quantidade`   | Retorna a quantidade de pedidos do cliente|
| GET    | `/clientes/{id}/pedidos`      | Lista os pedidos de um cliente            |

---

## 🗂️ Modelagem da Base de Dados

### 📄 Tabela `pedidos`
- `id` (PK)
- `codigo_pedido`
- `codigo_cliente`
- `valor_total`

### 📄 Tabela `itens`
- `id` (PK)
- `pedido_id` (FK)
- `produto`
- `quantidade`
- `preco`

---

## 🖥️ Plano de Trabalho

| Tarefa                           | Estimado | Real | Status |
| --------------------------------- | -------- | ---- | ------ |
| Criação do repositório e estrutura| 1h       | 1h   | ✅     |
| Modelagem da base de dados        | 1h       | 1h   | ✅     |
| Configuração do RabbitMQ          | 1h       | 1h   | ✅     |
| Desenvolvimento do consumidor     | 2h       | 2h   | ✅     |
| Implementação da persistência     | 1.5h     | 1.5h | ✅     |
| Criação dos endpoints da API      | 2h       | 2h   | ✅     |
| Testes locais e com Postman       | 1h       | 1h   | ✅     |
| Escrita da documentação           | 2h       | 2h   | ✅     |

---

## 🔍 Execução Local com Docker Compose

```bash
docker-compose up --build
```

**Serviços disponíveis:**
- RabbitMQ: [http://localhost:15672](http://localhost:15672) (guest/guest)
- API: [http://localhost:8080](http://localhost:8080)

---

## 🧪 Testes Funcionais

- Publicação manual de mensagens no painel do RabbitMQ.
- Testes da API com Postman.
- Verificação de persistência no banco via DBeaver.
- Validação dos cálculos no endpoint `/pedidos/total`.

---

## 🔧 Arquitetura

```
+------------+         +----------------+         +-------------+
|  RabbitMQ  | ---->   |  Spring Boot   | ---->   | PostgreSQL  |
| (mensagem) |         | (consumer +    |         | (armazenar) |
+------------+         |   API REST)    |         +-------------+
```

---

## 📊 Diagramas

### 📌 Diagrama de Implantação (Docker)

```
[Container Spring Boot] -- conecta --> [Container PostgreSQL]
			 |
			 +-- escuta --> [Container RabbitMQ]
```

### 📌 Diagrama da Base de Dados

```
Pedido (1) -------- (N) Itens
```

---

## 🌐 Infraestrutura Local

- Docker Engine
- SO: Ubuntu 22.04 / Windows 10
- IDE: IntelliJ IDEA
- Java: OpenJDK 17
- PostgreSQL: versão 15 via Docker
- RabbitMQ: versão 3.13 via Docker

---

## 📎 Melhorias Futuras

- Implementar autenticação JWT na API
- Criar frontend com Angular ou React
- Adicionar cache Redis para consultas pesadas
- Persistência em MongoDB para maior flexibilidade
- Monitoramento com Prometheus + Grafana

---

## 📧 Exemplo de Mensagem RabbitMQ

Para testar o consumer, envie a seguinte mensagem JSON para a fila `pedidos.queue` através do painel de administração do RabbitMQ:

```json
{
	"codigoPedido": 1001,
	"codigoCliente": 1,
	"itens": [
		{
			"produto": "lápis",
			"quantidade": 100,
			"preco": 1.10
		},
		{
			"produto": "caderno",
			"quantidade": 10,
			"preco": 1.00
		}
	]
}
```

---

## 📮 Collection Postman

Importe a seguinte collection no Postman para testar os endpoints da API:

```json
{
	"info": {
		"name": "RequestsAPI - Sistema de Pedidos",
		"description": "Collection para testar os endpoints da API de pedidos",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "Valor Total do Pedido",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "{{base_url}}/pedidos/total/1001",
					"host": ["{{base_url}}"],
					"path": ["pedidos", "total", "1001"]
				}
			}
		},
		{
			"name": "Quantidade de Pedidos do Cliente",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "{{base_url}}/clientes/1/quantidade",
					"host": ["{{base_url}}"],
					"path": ["clientes", "1", "quantidade"]
				}
			}
		},
		{
			"name": "Listar Pedidos do Cliente",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "{{base_url}}/clientes/1/pedidos",
					"host": ["{{base_url}}"],
					"path": ["clientes", "1", "pedidos"]
				}
			}
		}
	],
	"variable": [
		{
			"key": "base_url",
			"value": "http://localhost:8080"
		}
	]
}
```

### 🔧 Como usar:
1. Importe a collection no Postman
2. Certifique-se de que a aplicação está rodando em `http://localhost:8080`
3. Execute os requests para testar os endpoints

---

## ✍️ Autor

Victor Viana  
GitHub: [@developerviana](https://github.com/developerviana)

> Projeto educacional para fins de avaliação técnica e portfólio.