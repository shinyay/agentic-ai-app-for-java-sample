# Dev Container Setup

This directory contains the configuration for running the TechKnowledge Assistant in a GitHub Codespaces or VS Code Dev Container environment.

## Files

- **Dockerfile**: Development environment with Java 17, Maven, and essential development tools
- **docker-compose.yml**: Docker Compose configuration for the development environment
- **devcontainer.json**: VS Code Dev Container configuration with extensions and settings

## Features

- ✅ Java 17 JDK
- ✅ Maven 3.9.6
- ✅ VS Code Java extensions
- ✅ Spring Boot extensions
- ✅ Debug port (5005) configured
- ✅ Application port (8080) forwarded
- ✅ Git, curl, vim, nano included

## Quick Start

1. **GitHub Codespaces**: Click the "Code" button and select "Open with Codespaces"
2. **VS Code**: Open the repository in VS Code and select "Reopen in Container"

## Available Commands

Once the container is running, you can use:

```bash
# Build the application
mvn clean compile

# Run tests
mvn test

# Package the application
mvn package

# Run the application
mvn spring-boot:run

# Check Java version
java -version

# Check Maven version
mvn -version
```

## Environment Variables

Set these environment variables in your Codespace secrets or .env file:

- `AZURE_OPENAI_API_KEY`: Your Azure OpenAI API key
- `AZURE_OPENAI_ENDPOINT`: Your Azure OpenAI endpoint
- `AZURE_OPENAI_DEPLOYMENT_NAME`: Your deployment name (default: gpt-4)
- `AZURE_OPENAI_EMBEDDING_DEPLOYMENT`: Your embedding deployment (default: text-embedding-ada-002)

## Debugging

The development container includes remote debugging support:
- Debug port: 5005
- JDWP transport configured automatically
- Attach your IDE debugger to localhost:5005