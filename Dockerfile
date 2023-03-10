# stage 1: build using gradle
FROM gradle:7-jdk17 AS build
COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

# stage 2: package into a lightweight image
FROM openjdk:17-jdk-alpine
COPY --from=build /home/gradle/src/build/libs/*.jar /app.jar
EXPOSE 8080
ENV ENV=prd
ENTRYPOINT ["java", "-jar", "/app.jar" , "--host", "0.0.0.0" ]

#COPY ./build/libs/*.jar ./app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prd" ]
