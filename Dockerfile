
# Stage 1: Build the Java application using Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven configuration and source code
COPY pom.xml .
COPY src ./src

# Compile, test, and package the application
RUN mvn -B clean package

# Stage 2: Run the application using a smaller Java runtime image
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the packaged JAR from the build stage
COPY --from=build /app/target/maven-java-project-0.0.1-SNAPSHOT.jar app.jar

# Start the Java application
ENTRYPOINT ["java", "-jar", "app.jar"]
