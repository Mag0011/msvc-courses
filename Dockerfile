FROM --platform=linux/amd64 python:3.7-alpine
FROM openjdk:11-ea-28-jdk as builder
ARG MSVC_NAME=msvc-courses
WORKDIR /app/$MSVC_NAME
COPY .mvn ./.mvn
COPY ./mvnw .
COPY pom.xml .
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target
COPY ./src ./src
RUN ./mvnw clean package -DskipTests
FROM openjdk:11-ea-28-jdk
ENV PORT 8002
EXPOSE ${PORT}
WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/msvc-courses/target/msvc-courses-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "msvc-courses-0.0.1-SNAPSHOT.jar"]

