# use a base image with Java 17
FROM openjdk:17-jdk-slim

# set the working directory inside the container
WORKDIR /app

# copy the JAR file into the container
COPY build/libs/lander-0.0.1-SNAPSHOT.jar app.jar

# copy Application.Properties file into container
COPY src/main/resources/application.properties ./

# expose the port your Spring application runs on
EXPOSE 8080

# command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]