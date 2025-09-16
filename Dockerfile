# =============================
# Etapa 1: Build com Maven + JDK 21
# =============================
FROM maven:3.9.4-eclipse-temurin-21 AS builder

# Diretório de trabalho
WORKDIR /app

# Copia arquivos do projeto
COPY pom.xml .
COPY src ./src

# Build do projeto sem testar, sem resource filtering
RUN mvn clean package -DskipTests

# =============================
# Etapa 2: Runtime com JDK 21 slim
# =============================
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copia o JAR gerado na etapa de build
COPY --from=builder /app/target/*.jar app.jar

# Porta que a API vai escutar
EXPOSE 8080

# Ativa o profile QA do Spring
ENV SPRING_PROFILES_ACTIVE=qa

# Comando para iniciar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]
