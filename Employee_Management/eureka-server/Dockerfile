FROM openjdk:23-jdk
WORKDIR /app
COPY target/eureka-server.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
