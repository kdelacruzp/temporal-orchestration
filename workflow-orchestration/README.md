# workflow-orchestration

This project uses Quarkus 3.5 and Temporal 1.18.2.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/
and for lear more abou Temporal, please visits its website https://docs.temporal.io/.

# Set up a local development environment for Temporal and Java

### Requirements previous
- JDK 17: https://www.oracle.com/pe/java/technologies/downloads/#java17 .
- Maven 3.9.X: https://maven.apache.org/download.cgi
- Local Temporal Cluster: https://learn.temporal.io/getting_started/java/dev_environment/

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

Temporal Orchestration Microservice:
```shell script
./mvnw compile quarkus:dev
```
> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8081/q/dev/.


Temporal Cluster:
```shell script
temporal server start-dev --db-filename your_temporal.db --ui-port 8080
```
> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/workflow-orchestration-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
