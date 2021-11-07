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

## 2_Eureka_Server_Registry:
    1. Service-Registry - Eureka Server implementation
    2. Enable eureka client configuration in both Order-Service and Payment Service.
    3. Change the URL of Order Service in Payment Service.
    4. Provide eureka-client maven dependency with cloud dependencymanagement and spring cloud version.
    5. Run service-registry application first (http://localhost:8761/)
    6. Then run order-service and payment-service.
    7. Every service should be registered with Eureka Server now.

## 3_Cloud_Gateway_Implementation:
    Till now when we are calling Order-Service and Payment-Service, we are invoking corresponding individual URL. There should be a common service where all the entry point should be registered and request should be invoked to this URL.
    All the requests can be made to a single endpoint i.e. http://localhost:7090
    ```
    http://localhost:7090/api/v1/orders/
    http://localhost:7090/api/v1/payment/
    ```

## 4_Config_Server:
    Common Eureka related configuration can be placed in a centralized store from where application configuration can be loaded.
    Application should be started in below order:
    1. Service-Registry
    2. Config-Service
    3. Cloud-Gateway
    4. Order-Service
    5. Payment-Service