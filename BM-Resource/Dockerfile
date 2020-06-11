FROM openjdk:13-alpine

EXPOSE 8081

ENV SPRING_PROFILES_ACTIVE=docker
ARG JAR_FILE=./target/*.jar

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]