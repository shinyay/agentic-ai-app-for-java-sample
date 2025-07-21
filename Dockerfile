FROM openjdk:17-jdk-slim

# Application metadata
LABEL maintainer="shinyay <shinya.com@gmail.com>"
LABEL description="TechKnowledge Assistant - Enterprise AI-Powered Technical Knowledge Management Platform"
LABEL version="0.0.1-SNAPSHOT"

# Create application directory
WORKDIR /app

# Copy Maven build artifact
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Create non-root user for security
RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup
RUN chown -R appuser:appgroup /app
USER appuser

# Application configuration
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/api/dev/health || exit 1

# Application startup
ENTRYPOINT ["java", "-jar", "/app/app.jar"]