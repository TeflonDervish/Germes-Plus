FROM amazoncorretto:17

WORKDIR /app

COPY target/germesplus-1.0.jar germes-plus.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "germes-plus.jar"]