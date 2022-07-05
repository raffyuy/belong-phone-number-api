# Belong Phone Number Service 
This is a Phone number service built using Java, Gradle, and Spring Boot

Assumptions:
1. Lists require no pagination 
2. Will use Relational DB 
3. No error handling necessary
4. No security necessary
5. Minimal fields in models/schema
6. Phone activation = simple boolean flag
7. "phone numbers as static data" - h2 in-memory database with initial data in data.sql

## How to Run

* Use JDK 11+ 
* Clone this repository
* Build project with ```./gradlew clean build```
* Run the project wiht
```
    gradle bootRun
```

## About the Service

The service is a simple phone number REST service. Data is stored in an H2 (in-memory) database. Sample data 
can be found in main/java/resources/data.sql

### Get list of all phone numbers

```
GET http://localhost:8080/belong/api/v1/phonenumbers
```

### Get list of all phone numbers belonging to a customer

```
GET http://localhost:8080/belong/api/v1/customers/{customerID}/phonenumbers 
```

### Actiave a specific phone number
```
PATCH http://localhost:8080/belong/api/v1/phonenumbers/{number}/activate
```

### Swagger API docs

http://localhost:8080/belong/swagger-ui/index.html
