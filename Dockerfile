FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /workspace

# Copy project files and build
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw && ./mvnw -DskipTests package -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /workspace/target/docs-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080
ENV JAVA_OPTS="-Xms256m -Xmx512m"
CMD bash -c 'echo "Waiting for DB..."; for i in {1..60}; do nc -z db 3306 && break || sleep 1; done; java $JAVA_OPTS -jar /app/app.jar'
