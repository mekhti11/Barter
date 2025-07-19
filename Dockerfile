FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy gradle wrapper and build files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copy source code
COPY src src

# Make gradlew executable
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew clean bootJar -x check -x test

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-Dserver.port=${PORT}", "-Dspring.profiles.active=prod", "-jar", "build/libs/barter-1.0.0-boot.jar"]