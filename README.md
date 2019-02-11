# Movie Services Api

This project brings film details to the user. If the desired film is not included in the system, the details to the user by sending a request to OMDB Api.

## Installation Guide

- docker-compose up -d

## Questions ?

- Swagger: Project has a one controller. This controller plays role get a film information. Swagger provides to acces to controller.
swagger-url : localhost:8080/swagger-ui.html
- If I had more time I refactored the service class.
- Used Spring Cache. Spring Cache easy way this project. Response is not very large, choice was made.
- JPA: JPA is a Java Persistance Api , jpa allows us management the relational data. This project used the spring data mongodb.
