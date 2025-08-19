# 📌 API de Foros - Spring Boot

Este proyecto es una API REST desarrollada con **Spring Boot** para la gestión de tópicos (comentarios/dudas) en un foro.  
Permite registrar, consultar, actualizar y eliminar tópicos, guardando la información en una base de datos relacional (mysql).

---

## ⚙️ Tecnologías utilizadas
- **Java 21LTS**
- [Spring Boot Starter Data JPA](https://spring.io/projects/spring-data-jpa) – Persistencia de datos
- [Spring Boot Starter Security](https://spring.io/projects/spring-security) – Seguridad y autenticación
- [Spring Boot Starter Validation](https://hibernate.org/validator/) – Validaciones
- [Spring Boot Starter Web](https://spring.io/projects/spring-boot) – Desarrollo de API REST
- [Flyway Core & MySQL](https://flywaydb.org/) – Migración y versionado de base de datos
- [Java JWT (Auth0)](https://github.com/auth0/java-jwt) – Generación y validación de tokens JWT
- [MySQL Connector](https://dev.mysql.com/downloads/connector/j/) – Conexión con MySQL
- [Project Lombok](https://projectlombok.org/) – Reducción de boilerplate en Java
- [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools) – Hot reload en desarrollo
- **Testing**
  - Spring Boot Starter Test
  - Spring Security Test

---

## 📌 Endpoints principales

La API sigue el patrón REST y expone los siguientes endpoints:

### 🔐 Autenticación
- `POST /login` → Recibe usuario y contraseña, devuelve un **JWT válido** para acceder a los demás endpoints.

### 📂 Tópicos
- `POST /topicos` → Registra un nuevo tópico.  
  Requiere un body con los datos del tópico. La fecha de creación se asigna automáticamente.
- `GET /topicos` → Obtiene la lista de todos los tópicos registrados.
- `GET /topicos/{id}` → Obtiene la información de un tópico específico.
- `PUT /topicos/{id}` → Actualiza los datos de un tópico existente. Sólo puede actualizar el título y el mensaje del tópico.
- `DELETE /topicos/{id}` → Elimina un tópico por su ID. Sólo lo desactiva, no lo elimina de la base de datos.

## 🛠️ Configura la base de datos en application.properties:
spring.datasource.url = jdbc:mysql://localhost:3306/foro_hub
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
spring.datasource.username = ${USER_MYSQL}
spring.datasource.password = ${DB_PASSWORD}
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

## 🧪 Pruebas del CRUD
Para probar los endpoints se utilizó Insomnia.
Solo necesitas importar el JSON de configuración de las requests o crear las peticiones manualmente.
