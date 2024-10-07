FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY target/luxmed-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
