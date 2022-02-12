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
    Configuration should be loaded after starting the servers, not during loading the eureka client microservice. Hence this configuration should be kept in bootstrap file.
    Application should be started in below order:
    1. Service-Registry
    2. Config-Service
    3. Cloud-Gateway
    4. Order-Service
    5. Payment-Service

## 5_Circuit_Breaker

    Here we are using Hystrix as Circuit Breaker framework.
    Steps need to follow:
    1. Add Maven dependency in pom.xml (spring-cloud-starter-netflix-hystrix)
    2. Add @EnableCircuitBreaker in main class
    3. Add @HyxtrixCommands in service level which needs circuit breaker
    4. Configure different hystrix behavior

## 6_Circuit_Breaker_Bulkhead_Pattern

    In this pattern, dedicated threadpool will be allotted to separate hystrix service. In this way if one of the service is slow, other services will not be affected.
    It's very easy to configure. You just need to play around the different parameters of HystrixCommand

    **When to use BulkHead Design Pattern:**
    1. Apply the bulkhead pattern whenever you want to scale a service independent of other service.
    2. Isolate resources used to consume a set of backend services, especially if the application can provide some level of functionality even when one of the service is not responding.
    3. Apply the bulkhead pattern to fault isolate components of varying risk or availability requirements.
    4. Protect the application from cascading failure.

## 7_Resilience4j_Circuit_Breaker

    Add following dependencies in Payment Service
    1. Resilience4j from io.github.resilience4j
    2. spring-boot-starter-aop
    3. spring-boot-starter-actuator
    4. Add the configuration in pom.xml
    5. Add @CircuitBreaker details in common.service file.

For testing, you should make Order-Service down.
Now try to access the below endpoint:

```
curl --location --request POST 'http://localhost:7090/api/v1/payment/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "paymentAmount": 90,
    "orderId": 1,
    "paymentMode": "Cheque"
}'
```

To get the Healthcheck of application:
http://localhost:8002/actuator/health

```
{
"status": "UP",
"components": {
"circuitBreakers": {
"status": "UNKNOWN",
"details": {
"orderService": {
"status": "CIRCUIT_HALF_OPEN",
"details": {
"failureRate": "-1.0%",
"failureRateThreshold": "50.0%",
"slowCallRate": "-1.0%",
"slowCallRateThreshold": "100.0%",
"bufferedCalls": 0,
"slowCalls": 0,
"slowFailedCalls": 0,
"failedCalls": 0,
"notPermittedCalls": 0,
"state": "HALF_OPEN"
}
}
}
},
"db": {
"status": "UP",
"details": {
"database": "PostgreSQL",
"validationQuery": "isValid()"
}
},
"discoveryComposite": {
"status": "UP",
"components": {
"discoveryClient": {
"status": "UP",
"details": {
"services": [
"payment-service",
"cloud-gateway",
"config-server"
]
}
},
"eureka": {
"description": "Remote status from Eureka server",
"status": "UP",
"details": {
"applications": {
"PAYMENT-SERVICE": 1,
"CLOUD-GATEWAY": 1,
"CONFIG-SERVER": 1
}
}
}
}
},
"diskSpace": {
"status": "UP",
"details": {
"total": 214748360704,
"free": 62370717696,
"threshold": 10485760,
"exists": true
}
},
"hystrix": {
"status": "UP"
},
"ping": {
"status": "UP"
},
"refreshScope": {
"status": "UP"
}
}
}
```

### Bulkhead with Resilience4j:
1. **Semaphore** - In this approach, we limit the number of concurrent requests to the service. It will reject the incoming requests once the limit hits.
2. **FixedThreadPoolBulkhead** - In this approach, we isolate a set of thread pool from system resources, using only that thread pool for the service. We also use a waiting queue apart from the thread pool, if both the thread pool and queue are full then in that case, the request will get rejected with BulkheadFullException.