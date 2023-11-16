# Stage 1: Build the application
FROM openjdk:11
<<<<<<< HEAD
EXPOSE 8089
=======
EXPOSE 8082
>>>>>>> 90e902f5eeda77ceb9d89de1c5e4b66bf05acd70
ADD target/kaddem-0.0.1.jar kaddem-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/kaddem-0.0.1.jar"]


