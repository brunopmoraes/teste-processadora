# Teste Processadora

## Como rodar

* Entre na pasta devops/docker/local

* Execute o comando:
```
    docker-compose up --build
```

## Testando o serviço

* Crie um Socket Client e conecte-se na porta 9999.
    - Ou execute o comando telnet para se conectar e envie uma mensagem no formato abaixo indicado, conforme exemplo:
```
    telnet 127.0.0.1 9999
    > { "action": "withdraw", "cardnumber":"1234123412341234", "amount": "1,10" }
```    

* O valor da entrada é uma string no formato de JSON:

```
    { "action": "withdraw", "cardnumber":"1234123412341234", "amount": "1,10" }
```

* O valor de saida é uma string no formato de JSON:

```
    { "action": "withdraw", "code":"00", "authorization_code": "123456" }
```

* Campo de retorno **code**: 
    - 00: aprovada.
    - 51: saldo insuficiente.
    - 14: conta inválida.
    - 96: erro de processamento.

## Massa de dados

| Número do Cartão | Valor Disponível |
| -----------------|------------------|
| 1234123412341234 |    1000          |
| 1234567890982312 |    1000          |

## Observação:

Para evitar que transações simultaneas alterem o mesmo cartão existe a procedure [sp_withdrawal_transaction](./src/main/resources/db/migrations/up/V0004__CREATE_PROCEDURE_WITHDRAWAL_TRANSACTION.sql), que da lock no registro que está sendo alterado em uma transação.