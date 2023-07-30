FROM openjdk:11-ea-17-jdk
WORKDIR /app
COPY ./target/msvc-courses-0.0.1-SNAPSHOT.jar .
EXPOSE 8002
ENTRYPOINT ["java", "-jar", "msvc-courses-0.0.1-SNAPSHOT.jar"]