FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw package -DskipTests
EXPOSE 8222
ENTRYPOINT ["java", "-jar", "target/api-gateway-0.0.1-SNAPSHOT.jar"]