# Reviews API 
Supports operations for writing reviewMongos and listing reviewMongos for a productMongo but with no sorting or filtering.

### Prerequisites
MySQL needs to be installed and configured. Instructions provided separately.

* Connects to your local MySQL instance and creates the reviewMongos database with the following command:
```
mysql> create database reviewMongos;
```

MongoDB needs to be installed and configured. Login to your local instance, with your user and password and then create the database:

```
use reviews
> db.createCollection("reviews")
```

### Running the Application

* Replace the user authentication for connect with your local database configuration. Password and username are located on application.properties.
```
spring.datasource.username=<db username goes here>
spring.datasource.password=<db password goes here>
```

### How to use this Application:
All the methos are listed on Swagger.
Swagger UI: http://localhost:8080/swagger-ui.html

### Features:
* MySQL Datasource in application.properties.
* Flyway scripts in src/main/resources/db/migration.
* JPA Entities and relationships.
* Spring Data JPA Repositories.
* Tests for JPA Repositories.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Testing In Spring Boot - TestEntityManager](https://www.baeldung.com/spring-boot-testing)
