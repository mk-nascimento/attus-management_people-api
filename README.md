# API REST Java Spring Boot Teste Attus

## Descrição

Esta API permite as seguintes operações:

- Pessoas: Criar, editar, deletar e recuperar uma ou mais pessoas.
- Endereços: Criar, editar, deletar e recuperar um ou mais endereços de uma pessoa.

O objetivo desta API é fornecer um conjunto de endpoints para gerenciar informações de pessoas e seus endereços de forma simples e eficiente. Com esta API, os usuários podem realizar operações CRUD (criar, ler, atualizar e deletar) em pessoas e seus endereços, bem como definir um endereço como principal para cada pessoa.

### Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Lombok
- Spring Boot DevTools
- Hibernate
- Banco de Dados PostgreSQL
- Banco de Dados H2 (para testes)
- Maven

## Endpoints

| Métodos | URLs                    | Ações                                 |
|---------|-------------------------|---------------------------------------|
| POST    | /api/person             | Criar uma nova Pessoa                 |
| GET     | /api/person/            | Recuperar todas as Pessoas            |
| GET     | /api/person/:id         | Recuperar uma Pessoa pelo `:id`       |
| PATCH   | /api/person/:id         | Atualizar uma Pessoa pelo `:id`       |
| DELETE  | /api/person/:id         | Excluir uma Pessoa pelo `:id`         |
| POST    | /api/person/:id/address | Criar um novo Endereço para uma Pessoa|
| GET     | /api/person/:id/address | Recuperar todos os Endereços de uma Pessoa |
| GET     | /api/address/:id        | Recuperar um Endereço pelo `:id`      |
| PATCH   | /api/address/:id        | Atualizar um Endereço pelo `:id`      |
| DELETE  | /api/address/:id        | Excluir um Endereço pelo `:id`        |
