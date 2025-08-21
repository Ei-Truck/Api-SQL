# Etapa de build com Maven + Java 21
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de runtime com JDK 21 slim
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080

# Ativa o profile QA
ENV SPRING_PROFILES_ACTIVE=qa

ENTRYPOINT ["java","-jar","app.jar"]