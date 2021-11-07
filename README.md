# SpringBoot-Microservice
This repo is for sample example of different components of Micro Services using Spring Boot and Spring Cloud

# Branch Details
## 1_Basic_Microservice_Implementation:
	1. Basic Order Service Implementation (Running on 8001 Port)
	2. Basic Payment Service Implementation (Running on 8002 Port)
	3. Call order service method from payment service - P2P call.
	4. 

```
http://localhost:8001/api/v1/orders/
{
    "item_name": "Pen",
    "quantity": 20,
    "price_per_item": 25.7
}

{
    "item_name": "Pencil",
    "quantity": 10,
    "price_per_item": 15.7
}
```

```
http://localhost:8002/api/v1/payment/
{
    "paymentAmount": 75,
    "orderId": 1,
    "paymentMode": "Cheque"
}
```
