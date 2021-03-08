# Introduction

This repository contains code used for the delivery of Spring Boot for the 21JANSDET2 cohort.

## How to run the application without compiling to a jar file

### Using the supplied mvnw Maven wrapper

```
./mvnw spring-boot:run
```

### Using a local installation of maven

```
mvn spring-boot:run
```

## How to package to a jar file and run the jar file

```
// Uses the maven compiler plugin supplied with a Spring Boot project
mvn package

// Runs the jar file using your local installation of Java
java -jar target/duck-demo-0.0.0-SNAPSHOT.java
```

# Acknowledgment

[Jordan Harrison](https://github.com/JHarry444/SpringDucks) - Author of project used to guide the lessons
