FROM openjdk:11-ea-17-jdk
WORKDIR /app/msvc-courses
COPY . .
RUN ./mvnw clean package -DskipTests
EXPOSE 8002
ENTRYPOINT ["java", "-jar", "./target/msvc-courses-0.0.1-SNAPSHOT.jar"]