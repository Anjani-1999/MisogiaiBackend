FROM gradle:jdk17-alpine AS builder
WORKDIR /app
COPY build.gradle .
COPY src/ /app/src/
RUN gradle clean build -x test --no-daemon
# Use AdoptOpenJDK for base image.
FROM openjdk:17-jdk-alpine AS runtime
RUN apk add --no-cache fontconfig ttf-dejavu
# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/build/libs/*-SNAPSHOT.jar /app.jar
# Run the web service on container startup.
CMD ["java", "-jar", "/app.jar", "--spring.profiles.active=${SPRING_BOOT_PROFILE}"]