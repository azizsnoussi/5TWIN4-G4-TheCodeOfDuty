# Stage 1: Build the application
FROM maven:3.6.3-openjdk-8 AS builder
WORKDIR /app
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline
COPY src/ src/
RUN --mount=type=cache,target=/root/.m2 mvn package

# Stage 2: Create the runtime container
FROM openjdk:8-jre-slim
EXPOSE 8082
COPY --from=builder /app/target/kaddem-1.0.jar /kaddem-1.0.jar
ENV JAVA_OPTS="-Dlogging.level.org.springframework.security=DEBUG -Djdk.tls.client.protocols=TLSv1.2"
ENTRYPOINT ["java", "-jar", "/kaddem-1.0.jar"]