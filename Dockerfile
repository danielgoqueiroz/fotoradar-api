FROM openjdk:17-jdk-slim-buster

COPY ./build/libs/fotoradar-0.0.1-SNAPSHOT.jar ./app.jar
ENV ENV=prd

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prd" ]
