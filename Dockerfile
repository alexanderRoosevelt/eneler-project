FROM adoptopenjdk:17-jdk-hotspot-bionic

WORKDIR /app

COPY build/libs/eneler-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Команда для запуска приложения
CMD ["java", "-jar", "app.jar"]
