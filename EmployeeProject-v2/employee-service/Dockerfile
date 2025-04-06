FROM openjdk:23-jdk
WORKDIR /app
COPY target/employee-service.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
