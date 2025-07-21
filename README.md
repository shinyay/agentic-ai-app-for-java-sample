# TechKnowledge Assistant

Enterprise AI-Powered Technical Knowledge Management Platform demonstrating comprehensive LangChain4j integration with Azure services.

## 🎯 Project Overview

TechKnowledge Assistant is a comprehensive enterprise-grade knowledge management and assistance platform that demonstrates the full capabilities of Microsoft's LangChain4j integration with Azure services. The application serves as a technical consultation hub for enterprise development teams, providing intelligent document management, code analysis, automated DevOps operations, and expert-level technical guidance.

## 🏗️ Technical Architecture

### Technology Stack
- **Backend Framework**: Spring Boot 3.2+
- **Java Version**: 17+
- **AI Framework**: LangChain4j 0.25.0
- **Cloud Platform**: Microsoft Azure
- **Build Tool**: Maven 3.9+
- **Security**: Spring Security with OAuth2 support

### Core Features Implemented

- ✅ **Technical Expert Service** - AI-powered technical consultation across multiple domains
- ✅ **Code Review Service** - Automated code analysis and review with security, performance, and quality assessments
- ✅ **Multiple AI Models** - Specialized models for different expertise areas (general, code-focused, architecture)
- ✅ **RESTful API** - Comprehensive REST endpoints for all AI services
- ✅ **Security Configuration** - Spring Security setup with CORS and authentication support
- ✅ **Development Endpoints** - Easy testing and health check endpoints

### Planned Features

- 🔄 **Document Processing Pipeline** - Intelligent document processing and storage
- 🔄 **Vector Store Integration** - Azure AI Search integration for semantic search
- 🔄 **RAG Implementation** - Retrieval-Augmented Generation for context-aware responses
- 🔄 **Intelligent Agents** - DevOps and Code Analysis agents with tool integration
- 🔄 **Real-time Chat** - WebSocket support for real-time communication
- 🔄 **Frontend Interface** - React-based user interface

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.9+
- Azure OpenAI Service access (for full functionality)

### Configuration

The application requires Azure OpenAI Service configuration. Set the following environment variables:

```bash
export AZURE_OPENAI_API_KEY=your-api-key
export AZURE_OPENAI_ENDPOINT=https://your-instance.openai.azure.com
export AZURE_OPENAI_DEPLOYMENT_NAME=gpt-4
export AZURE_OPENAI_EMBEDDING_DEPLOYMENT=text-embedding-ada-002
```

### Build and Run

```bash
# Clone the repository
git clone https://github.com/shinyay/agentic-ai-app-for-java-sample.git
cd agentic-ai-app-for-java-sample

# Build the application
mvn clean compile

# Run tests
mvn test

# Start the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📋 API Documentation

### Health and Information Endpoints

```bash
# Check application health
GET /api/dev/health

# Get application information
GET /api/dev/info

# Echo test
POST /api/dev/echo
```

### AI Services

#### Technical Consultation

```bash
POST /api/ai/technical-consultation
Content-Type: application/json

{
  "question": "How do I implement microservices architecture with Spring Boot?",
  "expertiseArea": "ARCHITECTURE",
  "context": "Optional context information"
}
```

**Expertise Areas**: `CLOUD`, `SECURITY`, `PERFORMANCE`, `ARCHITECTURE`, `CODE`, `GENERAL`

#### Code Review

```bash
POST /api/ai/code-review
Content-Type: application/json

{
  "code": "public class Example { ... }",
  "language": "java",
  "analysisType": "COMPREHENSIVE"
}
```

**Analysis Types**: `SECURITY`, `PERFORMANCE`, `QUALITY`, `COMPREHENSIVE`

#### Code Review with Improvements

```bash
POST /api/ai/code-review/with-improvements
Content-Type: application/json

{
  "code": "public class Example { ... }",
  "language": "java"
}
```

## 🔧 Development

### Project Structure

```
src/
├── main/
│   ├── java/com/example/techassistant/
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # REST controllers
│   │   ├── model/          # Domain models
│   │   ├── service/        # Business logic
│   │   │   └── ai/         # AI services
│   │   └── TechKnowledgeAssistantApplication.java
│   └── resources/
│       ├── application.yml
│       └── application-dev.yml
└── test/
    ├── java/               # Unit tests
    └── resources/
        └── application-test.yml
```

### Key Components

#### Configuration Classes
- `LangChain4jConfiguration` - Azure OpenAI model configuration
- `AzureIntegrationConfiguration` - Azure services setup
- `SecurityConfiguration` - Authentication and authorization

#### Service Classes
- `TechnicalExpertService` - Main AI consultation interface
- `CodeReviewService` - Code analysis and review functionality

#### Controller Classes
- `AiController` - Technical consultation endpoints
- `CodeReviewController` - Code review endpoints
- `DevController` - Development and testing endpoints

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=TechnicalExpertServiceTest

# Run tests with coverage
mvn test jacoco:report
```

## 🌐 Azure Integration

This application is designed to integrate with the following Azure services:

- **Azure OpenAI Service** - For chat and embedding models
- **Azure AI Search** - For vector storage and semantic search (planned)
- **Azure Cosmos DB** - For document and conversation storage (planned)
- **Azure Blob Storage** - For file storage (planned)
- **Azure Key Vault** - For secrets management (planned)

## 🔐 Security

- Spring Security with OAuth2 resource server support
- CORS configuration for cross-origin requests
- Rate limiting and input validation
- Secure configuration management

## 📊 Monitoring

- Spring Boot Actuator endpoints
- Health checks and metrics
- Structured logging with configurable levels

## 🧪 Testing Strategy

- Unit tests for core business logic
- Integration tests for API endpoints (planned)
- Test profiles for different environments

## 📝 Example Usage

### Technical Consultation Example

```bash
curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{
    "question": "What are the best practices for implementing caching in a microservices architecture?",
    "expertiseArea": "ARCHITECTURE"
  }'
```

### Code Review Example

```bash
curl -X POST http://localhost:8080/api/ai/code-review \
  -H "Content-Type: application/json" \
  -d '{
    "code": "public class UserService {\n    public User getUser(String id) {\n        return database.query(\"SELECT * FROM users WHERE id = \" + id);\n    }\n}",
    "language": "java",
    "analysisType": "SECURITY"
  }'
```

## 🔗 References

- [LangChain4j Documentation](https://docs.langchain4j.dev/)
- [Azure OpenAI Service](https://azure.microsoft.com/en-us/products/ai-services/openai-service)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

## 📄 License

Released under the [MIT license](https://gist.githubusercontent.com/shinyay/56e54ee4c0e22db8211e05e70a63247e/raw/f3ac65a05ed8c8ea70b653875ccac0c6dbc10ba1/LICENSE)

## 👤 Author

- GitHub: <https://github.com/shinyay>
- Twitter: <https://twitter.com/yanashin18618>
- Mastodon: <https://mastodon.social/@yanashin>
