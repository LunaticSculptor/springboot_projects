FROM openjdk:23-jdk
WORKDIR /app
COPY target/user-service.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
