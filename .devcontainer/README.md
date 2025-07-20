# VibeCode Studio - Dev Container

This directory contains the development container configuration for **VibeCode Studio**, providing a complete, reproducible development environment for the Java Spring Boot application.

## 📁 Dev Container Structure

The Dev Container is organized into three separate files for better maintainability:

- **`devcontainer.json`** - Main configuration with VS Code settings and extensions
- **`docker-compose.yml`** - Multi-service orchestration (app, PostgreSQL, pgAdmin)
- **`Dockerfile`** - Custom container image with all development tools

### File Responsibilities

| File | Purpose |
|------|---------|
| `devcontainer.json` | VS Code configuration, extensions, port forwarding, environment |
| `docker-compose.yml` | Service orchestration, volumes, networking, dependencies |
| `Dockerfile` | Base image setup, tool installation, MCP servers |

## 🚀 Quick Start

### Prerequisites
- **Visual Studio Code** with the [Dev Containers extension](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)
- **Docker Desktop** or **Docker Engine**

### Getting Started

1. **Open in Dev Container**:
   - Open this repository in VS Code
   - When prompted, click "Reopen in Container" 
   - Or: `Ctrl/Cmd + Shift + P` → "Dev Containers: Reopen in Container"

2. **Wait for Setup**: The container will automatically:
   - Install Java 17 and Maven
   - Install Azure CLI and Node.js
   - Set up PostgreSQL database
   - Install MCP (Model Context Protocol) servers
   - Install VS Code extensions
   - Run `mvn clean compile`

3. **Start Development**:
   ```bash
   # Run the application with development profile
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   
   # Or run tests
   mvn test
   ```

## 🏗️ Container Architecture

```
┌─────────────────────────────────────────────────────────┐
│                    Dev Container                        │
├─────────────────────┬───────────────────────────────────┤
│   Java App          │         PostgreSQL                │
│   Container          │         Container                 │
│                     │                                   │
│ • Java 17           │ • PostgreSQL 15                  │
│ • Maven 3.9         │ • Database: vibecode_studio      │
│ • Spring Boot       │ • User: vibecode                  │
│ • Azure CLI         │ • Auto-initialization             │
│ • Node.js 20        │                                   │
│ • MCP Servers       │                                   │
│ • VS Code Extensions│                                   │
└─────────────────────┴───────────────────────────────────┘
```

## 🛠️ Services & Ports

| Service | Port | Description |
|---------|------|-------------|
| **Spring Boot App** | `8080` | Main application API |
| **PostgreSQL** | `5432` | Primary database |
| **pgAdmin** | `8081` | Database management UI |

## 📊 Database Access

### PostgreSQL Connection
- **Host**: `postgres` (within container) or `localhost` (from host)
- **Port**: `5432`
- **Database**: `vibecode_studio`
- **Username**: `vibecode`
- **Password**: `vibecode123`

### pgAdmin Access
- **URL**: http://localhost:8081
- **Email**: `admin@vibecode.com`
- **Password**: `admin123`

### H2 Console (Development Profile)
When running with `dev` profile, you can also access the H2 in-memory database:
- **URL**: http://localhost:8080/api/h2-console
- **JDBC URL**: `jdbc:h2:mem:vibecode_dev`
- **Username**: `sa`
- **Password**: (empty)

## 🔧 Development Features

### Pre-installed VS Code Extensions
- **Java Extension Pack** - Complete Java development tools
- **Spring Boot Extensions** - Spring Boot development and dashboard
- **Maven for Java** - Maven integration
- **Azure CLI Tools** - Azure cloud operations and management
- **Thunder Client** - API testing (alternative to Postman)
- **GitLens** - Enhanced Git capabilities
- **Docker** - Container management

### Cloud & AI Integration
- **Azure CLI** - Manage Azure resources and services
- **MCP Servers** - Model Context Protocol for enhanced AI development
  - GitHub integration for repository operations
  - FileSystem access for project structure analysis
  - Brave Search for web research capabilities
  - SQLite for database operations
  - Memory for conversation context management

### Environment Variables
- `SPRING_PROFILES_ACTIVE=dev` - Activates development profile
- `AI_PROVIDER=mock` - Uses mock AI client for development
- `JAVA_HOME` - Java 17 installation path
- `MAVEN_HOME` - Maven installation path
- `NODE_PATH` - Node.js modules path for MCP servers
- `GITHUB_TOKEN` - (Optional) GitHub Personal Access Token for MCP
- `BRAVE_API_KEY` - (Optional) Brave Search API key for MCP

### Auto-completion & IntelliSense
- Java language support with error detection
- Spring Boot configuration auto-completion
- Maven dependency management
- Automatic imports and refactoring

## 🧪 Testing & Development Workflow

```bash
# Clean and compile
mvn clean compile

# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=GuardRailEngineTest

# Run with specific profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Package the application
mvn package

# Run integration tests
mvn verify

# Azure CLI operations (if authenticated)
az account show
az webapp list

# Test MCP servers
mcp list-servers
mcp inspect filesystem
```

## 🔍 Troubleshooting

### Container Won't Start
1. Ensure Docker is running
2. Check available disk space (>2GB recommended)
3. Restart Docker Desktop
4. Try: `Dev Containers: Rebuild Container`

### Database Connection Issues
1. Verify PostgreSQL container is running: `docker ps`
2. Check logs: `docker logs <postgres-container-name>`
3. Ensure port 5432 is not used by another service

### Java/Maven Issues
1. Rebuild container to get latest Java setup
2. Clear Maven cache: `mvn dependency:purge-local-repository`
3. Check Java version: `java -version` (should be 17)

### Performance Issues
1. Increase Docker memory allocation (recommended: 4GB+)
2. Close unnecessary VS Code extensions
3. Use `.gitignore` to exclude `target/` from file watching

## 📚 Additional Resources

- [Dev Containers Documentation](https://code.visualstudio.com/docs/devcontainers/containers)
- [Spring Boot Developer Tools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools)
- [VS Code Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
- [Azure CLI Documentation](https://docs.microsoft.com/en-us/cli/azure/)
- [MCP Integration Guide](.devcontainer/MCP_INTEGRATION.md) - Detailed MCP servers documentation
- [Azure AI Integration Guide](../AZURE_AI_INTEGRATION.md) - Azure AI Foundry setup

## 🔐 Security Notes

- The dev container uses default passwords for development convenience
- PostgreSQL and pgAdmin credentials are for development only
- For production deployment, use secure credentials and environment variables
- The mock AI provider is used by default to avoid API costs during development