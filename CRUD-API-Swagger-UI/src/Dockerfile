FROM maven:3.8.5-openjdk-17

WORKDIR /CRUD-API-Swagger-UI
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run