FROM amazoncorretto:17

WORKDIR /app

COPY target/germesplus-0.0.1-SNAPSHOT.jar germes-plus.jar

ENTRYPOINT ["java", "-jar", "germes-plus.jar"]