# E-commerce platform API

Welcome to the E-commerce platform API project! This service allows you to interact with the product catalog and perform various operations.


## Building and Running the Project

### Using Gradle

1. Clean and build the project using Gradle:

    ```bash
    ./gradlew clean build
    ```

2. Start the application:

    ```bash
    ./gradlew bootRun
    ```

### Using Docker

If you prefer to use Docker, you can build and start the application with the following command:

```bash
docker-compose up --build
```

## API Documentation

To explore the API endpoints, head over to the Swagger UI:

[Swagger UI](http://localhost:8080/swagger-ui/index.html)

### Metrics
Spring Boot Actuator provides various endpoints to monitor and manage your application.

To check the cache hit counter:

```bash
curl http://localhost:8080/actuator/metrics/cache.gets?tag=cache_result:hit
```

To check the cache miss counter:
```bash
curl http://localhost:8080/actuator/metrics/cache.gets?tag=cache_result:miss
```

To check the product fetch latency:
```bash
curl http://localhost:8080/actuator/metrics/product.fetch.latency
```

