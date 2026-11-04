# FinControl

Sistema de controle financeiro desenvolvido em Java, com integração ao banco de dados MySQL.

## Objetivo

Permitir o gerenciamento de receitas e despesas, auxiliando o usuário na organização de suas movimentações financeiras.

## Tecnologias utilizadas

- Java 8
- IntelliJ IDEA
- MySQL
- JDBC (MySQL Connector/J)

## Funcionalidades

- Cadastrar receitas e despesas
- Listar movimentações financeiras
- Atualizar movimentações
- Excluir movimentações
- Validar dados e tratar exceções

## Estrutura do projeto

- `Main.java`: menu principal e interação com o usuário.
- `Conexao.java`: conexão com o MySQL.
- `MovimentacaoDAO.java`: operações CRUD no banco de dados.
- `Movimentacao.java`: classe principal das movimentações.
- `Receita.java` e `Despesa.java`: subclasses de `Movimentacao`.

## Como executar

1. Instale o Java 8, o MySQL e o IntelliJ IDEA.
2. Execute o script `fincontrol.sql` no MySQL.
3. Abra o código-fonte no IntelliJ.
4. Adicione o driver MySQL Connector/J 8.0.33 ao projeto.
5. Configure a variável de ambiente `FINCONTROL_DB_PASSWORD` com a senha local do MySQL.
6. Verifique se o usuário e a porta do banco correspondem à configuração de `Conexao.java`.
7. Execute a classe `Main.java`.

## Banco de dados

- Banco: `fincontrol`
- Tabela: `movimentacoes`

## Conceitos aplicados

CRUD, programação orientada a objetos, herança, encapsulamento, JDBC e tratamento de exceções.
