# edulivery
Sistema de notificação de pedidos

POST
http://localhost:8080/pedidos

{
"cliente": "Teste H2",
"email": "teste@h2.com",
"telefone": "+551111111111",
"valor": 100.00,
"preferenciaNotificacao": "EMAIL"
}

POST
http://localhost:8080/pedidos/1/confirmar

H2
http://localhost:8080/h2-console/

JDBC URL
jdbc:h2:mem:pedidosdb