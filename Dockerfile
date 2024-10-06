FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY target/company-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/luxmed.jar"]
