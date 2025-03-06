FROM amazoncorretto:17

WORKDIR /app

COPY target/germesplus-0.0.1-SNAPSHOT.jar germes-plus.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "germes-plus.jar"]