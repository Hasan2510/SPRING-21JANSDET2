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

## Installing Spring Boot Tool Suite using the Eclipse Marketplace

1. Navigate to the Eclipse Marketplace

![image](https://user-images.githubusercontent.com/67016030/110522062-69390c80-8108-11eb-9e7d-e8fbfd56a0dc.png)

2. Search for 'Spring Tools Suite', install the relevant version

![image](https://user-images.githubusercontent.com/67016030/110522342-b2895c00-8108-11eb-8f95-5e0ec832b5bb.png)

## Creating a new Spring Boot project

1. Navigate to `File -> New -> Other`

![image](https://user-images.githubusercontent.com/67016030/110522797-393e3900-8109-11eb-960e-d70c126e3ea8.png)


2. Search for `Spring` and select `Spring Starter Project`

![image](https://user-images.githubusercontent.com/67016030/110522858-49561880-8109-11eb-90f4-7dcfa5d5b887.png)

3. Configure your project settings

![image](https://user-images.githubusercontent.com/67016030/110522968-6db1f500-8109-11eb-9aa6-df0cb7c7d901.png)

4. Select any dependencies you might need, the following selected in the image are good for a simple REST API

![image](https://user-images.githubusercontent.com/67016030/110523205-b23d9080-8109-11eb-83a6-b23407cd3c24.png)

## What is a Spring Bean

A Spring Bean is simply a managed object, managed by Spring that is... This is called Inversion of Control and also makes the object a candidate for dependency injection. Spring Beans' are stored in the `Application Context`.

We can register a bean in multiple ways, the simplest is to create a configuration class that holds our bean definitions:

```
@Configuration // Registers the class as a bean, this is a specialised bean that is used for providing beans from our bean definitions
public class Config {

  @Bean // Registers a method as a bean here
  @Scope("prototype") // A new object is generated everytime we call this bean
  public String getSomething() {
    return "Hello World!";
  }
}
```

As the configuration class is registered to Spring with `@Configuration`, it will be scanned during the component scan (other beans will be picked up as when correctly annotated) that occurs when your Spring Boot app is bootstrapping itself.

## What is @SpringBootApp

`@SpringBootApp` is a convenience annotation that will do multiple different things, including bootstrapping the application with a pre-made configuration where possible. THIS MUST BE APPLIED TO THE CLASS THAT CONTAINS YOUR `main` METHOD! One of the annotations that `@SpringBootApp` will run is the `@ComponentScan` annotation. This will recursively scan from the current package, and all sub-packages, for any classes labelled as beans and add them to the Application Context.

# Acknowledgment

[Jordan Harrison](https://github.com/JHarry444/SpringDucks) - Author of project used to guide the lessons
