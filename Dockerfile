from amazoncorretto:17

workdir /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x mvnw  # Даем права на выполнение

COPY src ./src

RUN ./mvnw package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/germesplus-0.0.1-SNAPSHOT.jar"]