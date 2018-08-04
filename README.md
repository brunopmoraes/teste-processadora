# Teste Processadora

## Como rodar

* Entre na pasta devops/docker/local
* Execute o comando:
```
    docker-compose up --build
```
## Testando o servico

* Crie um Socket Client e conecte-se na porta 9999.
* O valor da entrada é uma string no formato de JSON:
```
    {
        "action": "withdraw",
        "cardnumber":"1234567890123456",
        "amount": "1,10"
    }
```
* O valor de saida é uma string no formato de JSON:
```
    {
        "action": "withdraw",
        "code":"00",
        "authorization_code": "123456"
    }
```
* Campo de retorno **code**: 
    - 00: aprovada.
    - 51: saldo insuficiente.
    - 14: conta inválida.
    - 96: erro de processamento.