# Use Eclipse Temurin (OpenJDK 21) base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the jar file into the image
COPY target/blog-app.jar app.jar

# Expose the port your app runs on (default 8080)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
