# parking-control-api
Doing some study on Rest APIs with Java and Spring Boot

# About the API
An API for managing parking spots inside a condominum. It's built with Java and Spring Framework. It has the only porpose to study the tecnologies that are used to build an API with Java.

# Features
This API provides HTTP endpoint's for the following:
* Create a parking spot: POST/parking-spot
* Update a parking spot: PUT/parking-spot/{id}
* Delete a parking spot (by id): DELETE/parking-spot/{id}
* Get all parking spots: GET/parking-spot
* Get all parking spots but filtering by block: GET/parking-spot?block=F
* Get one parking spot (by id): GET/parking-spot/{id}
* Get one parking spot (by parking spot number): GET/parking-spot/number/{number}

# This project was developed with: 

* Java 11
* Spring Framework
* Maven
* PostgreSQL
* Swagger 3.0.0
* Mapstruct 1.5.2
* Lombok
 
# Compile and build

mvn install

# Execution

You will need to have PostgreSQL installed in your machine to run the API database. After installed, you will have to create a database named parking-control-db.

CREATE database parking-control-db;

After creating the database, you will need to add your Postgres root username and password in the application.properties file on src/main/resource.

# Run

You can run the application localy using:
mvn spring-boot:run

By default, the API will be avalaible at http://localhost:8080/parking-spot

# Documentation

You can find the documentation made by Swagger at http://localhost:8080/swagger-ui
