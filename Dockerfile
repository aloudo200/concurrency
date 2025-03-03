# Use an official OpenJDK 23 runtime as a parent image
FROM openjdk:25-slim

ENV SPRING_PROFILES_ACTIVE=docker

# Set the working directory in the container
WORKDIR /app

ARG VERSION

# Copy the jar file into the container at /app
COPY target/concurrency-${VERSION}-SNAPSHOT.jar app.jar

RUN if grep -qE "(docker|containerd)" /proc/1/cgroup; \
    then echo "docker" > /app/profile; \
    else echo "default" > /app/profile; fi

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]