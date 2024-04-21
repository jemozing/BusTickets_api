# Stage 1: Сборка приложения
FROM gradle:8.7-jdk21-alpine as build
WORKDIR /app
COPY build.gradle settings.gradle /app/
RUN gradle dependencies --no-daemon # Кеширование зависимостей
COPY . /app
RUN gradle build --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/busticket_api-1.0.jar /app/*.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/*.jar"]