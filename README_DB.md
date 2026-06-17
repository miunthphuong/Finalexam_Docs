MySQL local setup (docker)

1. Start MySQL with Docker Compose:
   docker-compose up -d

2. Defaults (can be overridden via env):
   JDBC_URL: jdbc:mysql://localhost:3306/docsdb?useSSL=false&serverTimezone=UTC
   JDBC_USER: docsuser
   JDBC_PASSWORD: docs_pass

3. Build and run the app:
   ./mvnw -DskipTests package
   ./mvnw spring-boot:run

4. Notes:
 - The project runs SQL in src/main/resources/data.sql on startup to seed courses, students and enrollments.
 - If using an external MySQL instance, set env vars: JDBC_URL, JDBC_USER, JDBC_PASSWORD
 - To inspect DB: docker exec -it docs-mysql mysql -u docsuser -pdocs_pass docsdb
