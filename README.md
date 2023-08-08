![Tela inicial da IBM Tours](https://media.discordapp.net/attachments/924735264574816318/1138572948198932621/home_screen.jpg?width=775&height=379)

# IBM Tours

IBM Tours é um sistema que permite que os hóspedes façam reservas em casas de temporada. Esse projeto envolve a criação de uma interface amigável e a integração com API Rest para atender às regras de negócio exigidas. Para isso, foram usados:

- Java 17
- Spring Boot 3.1.2
- Banco em memória (H2)
- Bean Validation
- Arquitetura MVC, baseada no acoplamento de classes como domain, repository, dto e service.

Atenção: este repositório contém apenas o **back end** da aplicação **IBM Tours**, desenvolvido para a avaliação técnica da IBM.

## ⚙️ O que você irá encontrar aqui

### Code

:heavy_check_mark: Cadastro de uma API Rest em Spring

:heavy_check_mark: Testes unitários dos principais métodos

:heavy_check_mark: Arquitetura e organização de código baseada em padrões de mercado

## Demonstração

Veja um vídeo curto de demonstração do sistema, front end integrado ao back end. Clique no link abaixo:

[IBM Tour - Video Demonstração](https://www.youtube.com/embed/UQdPPougiPo)

## Iniciando a aplicação

A aplicação está configurada para ser executada na porta 8080, certifique-se de que a mesma esteja livre.
Ao descompactar o arquivo e importá-lo em sua IDE, basta iniciá-lo da forma padrão.

## Documentação da API

### OBTER TODAS AS RESERVAS (GET ALL)


```http
    GET       http://localhost:8080/reservas
```
#### Retorno esperado:
```
[
    {
        "id": 1,
        "nomeHospede": "Alexandre",
        "dataInicio": "2021-06-11",
        "dataFim": "2023-08-17",
        "quantidadePessoas": 5,
        "status": "PENDENTE"
    },
    {
        "id": 2,
        "nomeHospede": "Fulano de Tal",
        "dataInicio": "2023-08-10",
        "dataFim": "2023-08-15",
        "quantidadePessoas": 1,
        "status": "CONFIRMADA"
    }
    ...
]
```
Se não houver registros, o retorno será um array vazio `[]`.

---

### OBTER UMA RESERVA (GET BY ID)

```http
  GET       http://localhost:8080/reservas/{id}
```

| Parâmetro   | Tipo     | Descrição                                           |
| :---------- |:---------|:----------------------------------------------------|
| `id`      | `number` | **Obrigatório**. O ID do item que você quer buscar. |


##### Retorno esperado:

```
{
    "id": 1,
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
}
```
- Um único ID deve ser inserido
- Caso não haja registro para o ID informado, receberá uma exceção personalizada.

---

### CRIAR UMA RESERVA (CREATE)

```http
  POST       http://localhost:8080/reservas
```
#### Corpo da requisição
```
{
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 1
}
```
#### Retorno esperado:

```
{
    "id": 1,
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
}
```
Por padrão, o status será definido como "CONFIRMADA". 

---

### ATUALIZAR UMA RESERVA (UPDATE)

```http
  UPDATE       http://localhost:8080/reservas
```
#### Corpo da requisição
```
{
    "nomeHospede": "Alexandre",
    "dataInicio": "2021-06-11",
    "dataFim": "2023-08-17",
    "quantidadePessoas": 5,
    "status": "PENDENTE"
}
```
#### Retorno esperado:

```
{
    "id": 1,
    "nomeHospede": "Alexandre",
    "dataInicio": "2021-06-11",
    "dataFim": "2023-08-17",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
}
```

- Existem apenas três tipos de status disponíveis: CONFIRMADA, PENDENTE OU CANCELADA. O envio deve ser feito em **UPPERCASE**.

---

### CANCELAR UMA RESERVA (DELETE)

```http
  DELETE       http://localhost:8080/reservas/{id}
```

| Parâmetro   | Tipo     | Descrição                                           |
| :---------- |:---------|:----------------------------------------------------|
| `id`      | `number` | **Obrigatório**. O ID do item que você quer buscar. |


##### Retorno esperado:

```
{
    "id": 1,
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 4,
    "status": "CANCELADA"
}
```
- Um único ID deve ser inserido
- Caso não haja registro para o ID informado, receberá uma exceção personalizada.

---

> _“Se você se empenhar o suficiente pode fazer qualquer história resultar.” <div style="text-align: right"> Saul Goodman_ </div>
