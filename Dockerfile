# Stage 1: Build
FROM eclipse-temurin:25-jdk-alpine AS build

WORKDIR /app

# Kopiera Maven-filer
COPY pom.xml .
COPY src ./src

# Installera Maven och bygg projektet
RUN apk add --no-cache maven && \
    mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app

# Kopiera den byggda JAR-filen från build-steget
COPY --from=build /app/target/*.jar app.jar

# Starta applikationen
ENTRYPOINT ["java", "-jar", "app.jar"]