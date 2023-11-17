# Stage 1: Build the application
FROM maven:3.6.3-openjdk-8 AS builder
EXPOSE 8089
ADD target/kaddem-0.0.1.jar kaddem-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/kaddem-0.0.1.jar"]

# Stage 2: Create the runtime container
FROM openjdk:8-jre-slim
EXPOSE 8087
RUN apt-get update && apt-get install -y curl
ARG NEXUS_URL="http://192.168.33.10:8081/repository/maven-releases/"
ARG ARTIFACT_PATH="tn/esprit/spring/kaddem/0.0.1/kaddem-0.0.1.jar"
RUN curl -o /kaddem-0.0.1.jar ${NEXUS_URL}${ARTIFACT_PATH}
ENTRYPOINT ["java", "-jar", "/kaddem-0.0.1.jar"]
