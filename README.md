# VibeCode Studio - Agentic AI App for Java

**VibeCode Studio** is a collaborative, chat-first workspace for AI-powered software development. It's "Figma for vibe coding" - a platform where anyone can describe software in natural language, iterate in a tight human-AI loop, and deploy production-ready apps while maintaining security, quality, and cost guardrails.

## 🎯 Vision & Value Proposition

- **Idea → MVP in < 10 min**: Rapidly prototype applications through natural language conversations
- **50% developer-time savings** on prototyping vs. traditional IDE flows
- **Security grade A**: No critical vulnerabilities at release time through embedded guardrails

## ✨ Features

This Java backend implementation provides the core services for VibeCode Studio:

### 🛡️ GuardRail Engine (FR-3)
- **OWASP-LLM validation**: Prompt injection detection, output safety checks
- **Package allow-listing**: Strict mode validation of dependencies  
- **Security pattern detection**: Identifies common vulnerability patterns
- **Configurable policies**: Enable/disable specific security checks

### 💰 Cost Sentinel (FR-4)
- **Token tracking**: Monitor API usage and costs per project/user
- **Budget enforcement**: Alert at 80% of monthly limits, block at 100%
- **Real-time monitoring**: Track costs across LLM calls, builds, and deployments
- **Usage analytics**: Detailed cost breakdowns and projections

### 🤖 Agent Core (FR-1, FR-2)
- **Conversational project creation**: Generate projects from natural language
- **Iterative chat-edit**: Modify code through conversational interface
- **Context-aware responses**: Maintain conversation history and project context
- **Multi-format output**: Support for code generation, explanations, and refactoring

### 📊 Project Management
- **Lifecycle tracking**: Draft → Planning → Building → Testing → Deployed
- **Version control integration**: Git repository management
- **Cost attribution**: Per-project cost tracking and budgeting
- **Session management**: Organized chat sessions per project

## 🏗️ Architecture

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   REST APIs     │────│   Agent Core     │────│  GuardRail      │
│                 │    │                  │    │  Engine         │
├─────────────────┤    ├──────────────────┤    ├─────────────────┤
│ • Projects      │    │ • Chat Client    │    │ • OWASP Checks  │
│ • Chat Sessions │    │ • Cost Tracking  │    │ • Package Scan  │
│ • GuardRails    │    │ • Context Mgmt   │    │ • Security Rules │
│ • Cost Monitor  │    │ • Validation     │    │ • Pattern Match │
└─────────────────┘    └──────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌──────────────────┐
                    │  Cost Sentinel   │
                    │                  │
                    │ • Budget Limits  │
                    │ • Usage Alerts   │
                    │ • Token Counting │
                    │ • Cost Analytics │
                    └──────────────────┘
```

## 🚀 Getting Started

### Requirements

- Java 17+
- Maven 3.6+
- PostgreSQL 12+ (for production) or H2 (for development/testing)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/shinyay/agentic-ai-app-for-java-sample.git
cd agentic-ai-app-for-java-sample
```

2. **Configure application properties**
```bash
# Copy and edit application.properties
cp src/main/resources/application.properties.example src/main/resources/application.properties

# Set your database connection and OpenAI API key
export OPENAI_API_KEY=your-api-key-here
export DATABASE_URL=jdbc:postgresql://localhost:5432/vibecode_studio
```

3. **Build and run**
```bash
# Build the application
mvn clean compile

# Run tests
mvn test

# Start the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080/api`

### Usage

#### Create a Project
```bash
curl -X POST http://localhost:8080/api/projects/create \
  -H "Content-Type: application/json" \
  -d '{
    "prompt": "Build a real-time todo app with user authentication and WebSocket updates",
    "userId": "user123"
  }'
```

#### Start a Chat Session
```bash
curl -X POST http://localhost:8080/api/chat/sessions \
  -H "Content-Type: application/json" \
  -d '{
    "projectId": 1,
    "userId": "user123", 
    "sessionName": "Feature Development"
  }'
```

#### Generate Code
```bash
curl -X POST http://localhost:8080/api/chat/message \
  -H "Content-Type: application/json" \
  -d '{
    "sessionId": 1,
    "message": "Create a REST controller for managing todos with CRUD operations",
    "userId": "user123"
  }'
```

#### Validate Code with GuardRails
```bash
curl -X POST http://localhost:8080/api/guardrail/validate \
  -H "Content-Type: application/json" \
  -d '{
    "code": "public class TodoController { ... }",
    "prompt": "Create a todo controller"
  }'
```

## 🧪 Testing

The project includes comprehensive tests for all core components:

```bash
# Run all tests
mvn test

# Run specific test classes
mvn test -Dtest=GuardRailEngineTest
mvn test -Dtest=CostSentinelTest

# Run with coverage (if configured)
mvn test jacoco:report
```

## 📋 API Reference

### Projects API
- `GET /api/projects?userId={userId}` - List user projects
- `POST /api/projects/create` - Create project from prompt
- `GET /api/projects/{id}?userId={userId}` - Get project details
- `PUT /api/projects/{id}/status` - Update project status

### Chat API  
- `GET /api/chat/sessions?userId={userId}&projectId={projectId}` - List chat sessions
- `POST /api/chat/sessions` - Create new chat session
- `POST /api/chat/message` - Send message to AI agent
- `POST /api/chat/generate-code` - Generate code from prompt

### GuardRail API
- `POST /api/guardrail/validate` - Validate code and prompt
- `GET /api/guardrail/status` - Check GuardRail engine status

## ⚙️ Configuration

Key configuration properties:

```properties
# Cost Management
vibecode.cost.default-monthly-limit=100.0
vibecode.cost.alert-threshold=0.8

# GuardRail Engine
vibecode.guardrail.enabled=true
vibecode.guardrail.strict-mode=false
vibecode.guardrail.allowed-packages=org.springframework,com.fasterxml.jackson

# Security  
vibecode.security.jwt.secret=${JWT_SECRET}
vibecode.security.jwt.expiration=86400000
```

## 🛡️ Security Features

- **Prompt Injection Protection**: Detects and blocks malicious prompts
- **Code Safety Validation**: Prevents generation of dangerous code patterns
- **Package Whitelisting**: Restricts dependencies to approved packages
- **Secret Detection**: Identifies potential hardcoded secrets
- **Audit Logging**: Complete trail of all AI interactions

## 📊 Cost Management

- **Real-time Tracking**: Monitor API costs as they occur
- **Budget Enforcement**: Automatic limits and alerts
- **Usage Analytics**: Detailed breakdowns by user/project/time
- **Predictive Alerts**: Warnings before budget exhaustion

## 🔧 Development

### Project Structure
```
src/main/java/com/vibecode/studio/
├── VibeCodeStudioApplication.java     # Main application
├── config/                            # Configuration classes
├── controller/                        # REST API endpoints
├── model/                            # JPA entities
├── repository/                       # Data access layer
└── service/
    ├── agent/                        # AI agent core logic
    ├── cost/                         # Cost tracking and budgets
    └── guardrail/                    # Security validation
```

### Adding New Features

1. Create service classes in appropriate packages
2. Add REST endpoints in controller package
3. Write comprehensive tests
4. Update API documentation
5. Add configuration properties as needed

## 📈 Roadmap

Future enhancements aligned with the PRD:

- [ ] **Instant Preview & Deploy (FR-5)**: One-click deployment to managed hosting
- [ ] **Marketplace SDK (FR-6)**: Plugin framework for custom tools
- [ ] **Collaboration & Replay (FR-7)**: Multi-cursor editing and time-travel
- [ ] **Advanced Analytics**: Detailed usage and performance metrics
- [ ] **Enterprise Features**: SSO, advanced security, on-premise deployment

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

Released under the [MIT license](https://gist.githubusercontent.com/shinyay/56e54ee4c0e22db8211e05e70a63247e/raw/f3ac65a05ed8c8ea70b653875ccac0c6dbc10ba1/LICENSE)

## 👨‍💻 Author

- **GitHub**: <https://github.com/shinyay>
- **Twitter**: <https://twitter.com/yanashin18618>
- **Mastodon**: <https://mastodon.social/@yanashin>

---

*VibeCode Studio: Democratizing software creation through conversational AI while maintaining enterprise-grade security and cost controls.*
