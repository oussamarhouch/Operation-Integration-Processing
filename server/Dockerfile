FROM ubuntu:latest
LABEL authors="o.rhouch"

FROM openjdk:11
EXPOSE 8080
RUN mvn clean package
ADD target/batch-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]