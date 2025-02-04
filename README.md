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

To check the product fetch latency:
```bash
curl http://localhost:8080/actuator/metrics/product.fetch.latency
```

### Monitoring and Metrics
This project includes monitoring and metrics using Redis, Redis Exporter, Prometheus, and Grafana. Below are the steps to access the various endpoints:  
#### Redis
Redis is used as the caching layer. It is accessible on port 6379.  

#### Redis Exporter

Redis Exporter is used to export Redis metrics to Prometheus.

```browser
http://localhost:9121/metrics
```

#### Prometheus

```browser
http://localhost:9090
```

#### Grafana

Visualize metrics at:

```browser
http://localhost:3000
```

### Redis Monitoring with Grafana
To monitor your Redis instance using Grafana, follow these steps:  

1. Go to the [Grafana Dashboards website](https://grafana.com/grafana/dashboards/).  
2. Use the search bar to look for available Redis templates. You can search for keywords like "Redis" to find relevant dashboards.  
3. Browse through the search results and select a Redis template that suits your needs. Each template will have a detailed description and preview.  
4. Import the Template:  
* Copy the dashboard ID or JSON file of the selected template.
* Open your Grafana instance.
* Navigate to the "Dashboards" section and click on "Import".
* Paste the dashboard ID or upload the JSON file and follow the prompts to complete the import.
5. Ensure that your Grafana instance is configured to connect to your Redis data source. You may need to set up a Prometheus exporter for Redis if not already done.  
6. Once the template is imported and the data source is configured, you can start visualizing your Redis metrics on the Grafana dashboard. 
