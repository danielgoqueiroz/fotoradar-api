FROM openjdk:17-jdk-slim-buster

COPY ./build/libs/*.jar ./app.jar
ENV ENV=prd

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prd" ]
