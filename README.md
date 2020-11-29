# Widgets Service
Rest API that facilitates working with widgets

## Building the application
From the root of the project you need to run:

```mvn clean package```

## Running the application
After building, from the root of the project you need to run:

```java -jar target/widget-service-1.0.0-SNAPSHOT.jar```

## API documentation
Application exposes following REST endpoints
- POST - /api/v1/widgets - Create widget
- GET  - /api/v1/widgets - Get all widgets sorted by z index ascending
- GET  - /api/v1/widgets/{id} - Get widget by id
- PUT  - /api/v1/widgets/{id} - Update widget by id
- DELETE - /api/v1/widgets/{id} - Delete widget by id

## Swagger documentation
Application also exposes a swagger documentation available at the following link:

```http://localhost:8080/swagger-ui/index.html?url=/api-docs```

## Maven CI friendly
In order to facilitate easy versioning of jars in a fast CI/CD environment i also added
the maven ci friendly plugin.
With this you can run builds like this:

```mvn clean package -Drevision=100```

The resulting jar will have the name : widget-service-100.jar

## Configuration and Logging
Configuration and logging can be of course externalized using another yml properties for spring boot

```java -jar widget-service-1.0.0-SNAPSHOT.jar --spring.config.location=file:///home/config/externalConfig.yml```

## Code Coverage
Jacoco is used to do code coverage analysis.
There is a maven profile that can be used to trigger this analysis

```mvn clean verify -Pjacoco```

At the end of the execution if coverage is not according to jacoco config, build will fail with an error like below.
 
``` Rule violated for bundle widget-service: lines covered ratio is 0.79, but expected minimum is 0.89```

Of course, we can enable/disable if build fails or not.

## TODO 
- find an elegant solution for the gap issue

## Future functionality
- use different versions of WidgetDto depending on create/update
- improve converters to not create a new object every time, maybe implement BiFunction?
- create a docker container of the final jar
- implement database storage
- implement authentication/authorization
- implement rate limiting(this can also be provided by a platform like kubernetes)
- implement filtering
- implement request tracing(opentracing or zipkin are both good frameworks)
