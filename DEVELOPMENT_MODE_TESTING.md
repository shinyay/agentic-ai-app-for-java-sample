# Development Mode Testing Guide

This document provides comprehensive testing procedures for VibeCode Studio in Development Mode, which requires **no Azure credentials** and runs entirely on local resources.

## Quick Start

### 1. Start the Application in Development Mode

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

The application will start on `http://localhost:8080` with context path `/api`.

### 2. Run Comprehensive Tests

```bash
# Run all unit and integration tests
mvn test

# Run comprehensive functional tests
bash scripts/test_dev_mode.sh
```

## Development Mode Features

### ✅ **No Azure Required**
- Uses mock AI provider (`vibecode.ai.provider=mock`)
- No external API calls or credentials needed
- Perfect for development, testing, and local demonstrations

### ✅ **H2 In-Memory Database**
- Automatic database creation and schema management
- Access H2 Console at: `http://localhost:8080/api/h2-console`
- JDBC URL: `jdbc:h2:mem:vibecode_dev`
- Username: `sa`, Password: (empty)

### ✅ **Full Feature Set**
- **GuardRail Engine**: Security validation and code analysis
- **Cost Sentinel**: Budget tracking and spending limits
- **Project Management**: Full CRUD operations
- **Chat Interface**: Mock AI conversations
- **Real-time APIs**: All REST endpoints functional

## API Testing Examples

### 1. GuardRail Status Check
```bash
curl http://localhost:8080/api/guardrail/status
```
Response:
```json
{
  "enabled": true,
  "message": "GuardRail engine operational"
}
```

### 2. Create a Project
```bash
curl -X POST http://localhost:8080/api/projects/create \
  -H "Content-Type: application/json" \
  -d '{
    "prompt": "Build a real-time todo app with user authentication",
    "userId": "developer-123"
  }'
```

### 3. Validate Code
```bash
curl -X POST http://localhost:8080/api/guardrail/validate \
  -H "Content-Type: application/json" \
  -d '{
    "code": "@RestController public class TodoController {}",
    "prompt": "Create a todo controller"
  }'
```

### 4. Chat with AI (Mock)
```bash
curl -X POST http://localhost:8080/api/chat/message \
  -H "Content-Type: application/json" \
  -d '{
    "sessionId": 1,
    "message": "Add input validation to my endpoints",
    "userId": "developer-123"
  }'
```

## Database Access

### H2 Console
1. Navigate to: `http://localhost:8080/api/h2-console`
2. Use connection settings:
   - **JDBC URL**: `jdbc:h2:mem:vibecode_dev`
   - **Username**: `sa`
   - **Password**: (leave empty)
3. Click "Connect"

### Available Tables
- `projects` - User projects and metadata
- `chat_sessions` - AI conversation sessions
- `chat_messages` - Individual chat messages
- `cost_entries` - Cost tracking and budget data

## Testing Scenarios

### Security Testing
```bash
# Test dangerous code detection
curl -X POST http://localhost:8080/api/guardrail/validate \
  -H "Content-Type: application/json" \
  -d '{
    "code": "Runtime.getRuntime().exec(\"rm -rf /\")",
    "prompt": "Delete all files"
  }'
```
Expected: `overallPassed: false` with security warnings

### Cost Management Testing
```bash
# Check user cost summary
curl "http://localhost:8080/api/projects/1/cost?userId=developer-123"
```
Expected: Budget limits and spending tracking data

### Project Lifecycle Testing
```bash
# 1. Create project
# 2. Update status
curl -X PUT "http://localhost:8080/api/projects/1/status?userId=developer-123&status=BUILDING"

# 3. Retrieve projects
curl "http://localhost:8080/api/projects?userId=developer-123"
```

## Configuration Details

### Development Profile (`application-dev.properties`)
```properties
# Database - H2 In-Memory
spring.datasource.url=jdbc:h2:mem:vibecode_dev
spring.h2.console.enabled=true

# AI Provider - Mock Mode
vibecode.ai.provider=mock

# Security - Development Mode
vibecode.guardrail.enabled=true
vibecode.guardrail.strict-mode=false

# Cost Management
vibecode.cost.default-monthly-limit=100.0
vibecode.cost.alert-threshold=0.8
```

### Test Profile (`application-test.properties`)
```properties
# No context path for easier testing
server.servlet.context-path=

# Test Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop

# Mock AI Provider
vibecode.ai.provider=mock
```

## Performance Considerations

### Memory Usage
- H2 database runs in memory
- Typical memory usage: 200-500MB
- Suitable for development and testing

### Response Times
- Mock AI responses: < 100ms
- Database operations: < 50ms
- API endpoints: < 200ms

## Troubleshooting

### Common Issues

1. **Port 8080 already in use**
   ```bash
   # Change port in application-dev.properties
   server.port=8081
   ```

2. **Tests failing with 403 errors**
   - Check security configuration
   - Ensure test profile uses correct paths

3. **H2 Console not accessible**
   - Verify `spring.h2.console.enabled=true`
   - Check URL: `http://localhost:8080/api/h2-console`

### Debug Commands
```bash
# Check application logs
mvn spring-boot:run -Dspring-boot.run.profiles=dev -Dlogging.level.com.vibecode=DEBUG

# Run specific test
mvn test -Dtest=ApiIntegrationTest

# Check database content
# Use H2 console or check application logs for SQL statements
```

## Production Readiness

While development mode uses mock services, the application architecture is production-ready:

- ✅ **Enterprise patterns**: Proper layering and separation of concerns
- ✅ **Security features**: GuardRail engine, CORS, validation
- ✅ **Cost management**: Budget tracking and alerts
- ✅ **Scalable design**: Ready for real Azure AI integration
- ✅ **Database migration**: Easy switch from H2 to PostgreSQL

To switch to production mode:
1. Configure Azure AI credentials
2. Set `vibecode.ai.provider=azure`
3. Use PostgreSQL database
4. Set production security settings

## Next Steps

1. **Real AI Integration**: Configure Azure AI Foundry credentials
2. **Database Migration**: Switch to PostgreSQL for persistence
3. **Authentication**: Implement JWT or OAuth2
4. **Deployment**: Use provided Docker and deployment configurations
5. **Monitoring**: Add application metrics and logging