# 🚀 Quick Start Guide - TechKnowledge Assistant

> **Get up and running in 5 minutes with GitHub Codespaces**  
> Zero-config development environment for instant AI application development

[![Codespaces](https://img.shields.io/badge/GitHub-Codespaces-black.svg)](https://github.com/features/codespaces)
[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://openjdk.java.net/projects/jdk/17/)
[![Azure](https://img.shields.io/badge/Azure-OpenAI-blue.svg)](https://azure.microsoft.com/en-us/products/ai-services/openai-service)

## ⚡ 5-Minute Quick Start

### 🎯 What You'll Accomplish

```
✅ In 5 minutes you'll have:
├── 🚀 Running TechKnowledge Assistant application
├── 🧠 AI-powered technical consultation service
├── 🔍 Code review and analysis capabilities
├── 🌐 REST APIs ready for testing
├── 🐳 Complete development environment
└── ☁️ Azure integration configured
```

### 🚀 Step-by-Step (5 Minutes)

#### **⏱️ Minute 1-2: Launch Environment**

1. **Click the Codespaces button** in the GitHub repository:
   ```
   Code → Codespaces → Create codespace on main
   ```

2. **Wait for environment setup** (~2 minutes):
   - Container builds automatically
   - Java 17, Maven, Azure CLI installed
   - VS Code loads with all extensions

#### **⏱️ Minute 3: Configure Azure (Optional for Demo)**

If you have Azure OpenAI access:

```bash
# Set environment variables in terminal
export AZURE_OPENAI_API_KEY="your-api-key"
export AZURE_OPENAI_ENDPOINT="https://your-instance.openai.azure.com"
export AZURE_OPENAI_DEPLOYMENT_NAME="gpt-4"
```

**Without Azure:** The app runs in demo mode with mock responses.

#### **⏱️ Minute 4: Build and Run**

```bash
# Build the application
mvn clean compile

# Start the application
mvn spring-boot:run
```

#### **⏱️ Minute 5: Test Your Setup**

```bash
# Test health endpoint
curl http://localhost:8080/api/dev/health

# Test AI consultation (demo mode)
curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{"question": "What is Spring Boot?", "expertiseArea": "GENERAL"}'
```

**🎉 You're done! The application is running and ready for development.**

## 🎯 Different Paths Based on Your Goals

### 👨‍💻 **"I want to develop and extend this application"**

```bash
# After quick start above:

# 1. Explore the codebase
ls -la src/main/java/com/example/techassistant/

# 2. Run tests
mvn test

# 3. Make a simple change (try editing a controller)
# 4. Restart to see changes
mvn spring-boot:run

# 5. Explore API endpoints
curl http://localhost:8080/api/dev/info
```

**📖 Next steps:** 
- [Development Guide](../README.md#🔧-development-guide)
- [Architecture Guide](architecture-guide.md)
- [API Documentation](../README.md#📋-api-documentation)

### 🔧 **"I want to deploy this to Azure"**

```bash
# After quick start above:

# 1. Login to Azure
az login

# 2. Create Azure OpenAI resource (follow Azure guide)
# 3. Configure environment variables
# 4. Test with real Azure OpenAI

# 5. Deploy with Azure Developer CLI
azd init
azd up
```

**📖 Next steps:**
- [Azure Deployment Guide](azure-deployment.md)
- [Production Configuration](azure-deployment.md#🔧-production-configuration)

### 🏢 **"I want to understand the architecture"**

```bash
# After quick start above:

# 1. Explore the architecture
curl http://localhost:8080/api/dev/info

# 2. Test different expertise areas
curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{"question": "How to secure APIs?", "expertiseArea": "SECURITY"}'

# 3. Test code review
curl -X POST http://localhost:8080/api/ai/code-review \
  -H "Content-Type: application/json" \
  -d '{"code": "public class Test{}", "language": "java", "analysisType": "QUALITY"}'
```

**📖 Next steps:**
- [Architecture Guide](architecture-guide.md)
- [Main README](../README.md#🏗️-system-architecture)

## 🛠️ Development Workflow

### 🔄 Typical Development Session

```bash
# 1. Start your session
mvn spring-boot:run

# 2. Open another terminal for testing
# Codespaces: Terminal → New Terminal

# 3. Test your changes
curl http://localhost:8080/api/dev/health

# 4. Make code changes in VS Code editor

# 5. Quick restart (Ctrl+C in first terminal, then):
mvn compile spring-boot:run

# 6. Test your changes
curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{"question": "Test question", "expertiseArea": "GENERAL"}'
```

### 🧪 Testing Different Features

#### **Technical Consultation Examples**

```bash
# Architecture consultation
curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{
    "question": "What are microservices best practices?",
    "expertiseArea": "ARCHITECTURE",
    "context": "Building an e-commerce platform"
  }'

# Security consultation  
curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{
    "question": "How to implement OAuth2 in Spring Boot?",
    "expertiseArea": "SECURITY"
  }'

# Performance consultation
curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{
    "question": "How to optimize database queries?",
    "expertiseArea": "PERFORMANCE"
  }'
```

#### **Code Review Examples**

```bash
# Security-focused code review
curl -X POST http://localhost:8080/api/ai/code-review \
  -H "Content-Type: application/json" \
  -d '{
    "code": "public User getUser(String id) { return db.query(\"SELECT * FROM users WHERE id = \" + id); }",
    "language": "java",
    "analysisType": "SECURITY"
  }'

# Comprehensive code review
curl -X POST http://localhost:8080/api/ai/code-review \
  -H "Content-Type: application/json" \
  -d '{
    "code": "public class UserService { private List<User> users = new ArrayList<>(); public void addUser(User user) { users.add(user); } }",
    "language": "java",
    "analysisType": "COMPREHENSIVE"
  }'

# Code improvement suggestions
curl -X POST http://localhost:8080/api/ai/code-review/with-improvements \
  -H "Content-Type: application/json" \
  -d '{
    "code": "public void processData(List<String> data) { for(String item : data) { System.out.println(item); } }",
    "language": "java"
  }'
```

## 🔧 Customization Quick Start

### 🎯 Adding a New Expertise Area

1. **Add to enum:**
```java
// src/main/java/com/example/techassistant/model/ExpertiseArea.java
public enum ExpertiseArea {
    CLOUD, SECURITY, PERFORMANCE, ARCHITECTURE, CODE, GENERAL,
    DATABASE  // Add your new area
}
```

2. **Test the new area:**
```bash
mvn compile spring-boot:run

curl -X POST http://localhost:8080/api/ai/technical-consultation \
  -H "Content-Type: application/json" \
  -d '{"question": "How to optimize MySQL?", "expertiseArea": "DATABASE"}'
```

### 🎯 Adding a New API Endpoint

1. **Add to controller:**
```java
// src/main/java/com/example/techassistant/controller/AiController.java
@PostMapping("/quick-help")
public ResponseEntity<String> quickHelp(@RequestBody String question) {
    return ResponseEntity.ok("Quick help for: " + question);
}
```

2. **Test the new endpoint:**
```bash
mvn compile spring-boot:run

curl -X POST http://localhost:8080/api/ai/quick-help \
  -H "Content-Type: application/json" \
  -d '"How do I use Spring Boot?"'
```

## 🚨 Quick Troubleshooting

### ❓ Common Issues

#### **Port 8080 already in use**
```bash
# Find and kill the process
sudo lsof -i :8080
kill -9 <PID>

# Or use a different port
mvn spring-boot:run -Dserver.port=8081
```

#### **Azure OpenAI not working**
```bash
# Check environment variables
env | grep AZURE_OPENAI

# Test without Azure (demo mode)
unset AZURE_OPENAI_API_KEY
mvn spring-boot:run
```

#### **Build fails**
```bash
# Clean and rebuild
mvn clean
mvn compile

# Check Java version
java -version  # Should be 17+
```

#### **Container issues in Codespaces**
- **Reload window**: Ctrl+Shift+P → "Developer: Reload Window"
- **Rebuild container**: Ctrl+Shift+P → "Codespaces: Rebuild Container"

### 🆘 Getting Help

#### **Application logs**
```bash
# Check application logs
mvn spring-boot:run

# Enable debug logging
mvn spring-boot:run -Dlogging.level.com.example=DEBUG
```

#### **Health checks**
```bash
# Basic health
curl http://localhost:8080/api/dev/health

# Application info
curl http://localhost:8080/api/dev/info

# Echo test
curl -X POST http://localhost:8080/api/dev/echo \
  -H "Content-Type: application/json" \
  -d '{"message": "test"}'
```

## 🎯 Next Steps

### 📚 **Learn More**

After completing the quick start:

1. **Understand the Architecture**
   - [Architecture Guide](architecture-guide.md)
   - [Main README](../README.md#🏗️-system-architecture)

2. **Explore Development**
   - [Development Guide](../README.md#🔧-development-guide)
   - [API Documentation](../README.md#📋-api-documentation)

3. **Deploy to Production**
   - [Azure Deployment Guide](azure-deployment.md)
   - [Production Best Practices](azure-deployment.md#🛡️-security-best-practices)

### 🛠️ **Build Something**

Try building these features:

1. **New Consultation Domain**
   - Add "MOBILE" expertise area
   - Customize prompts for mobile development

2. **Enhanced Code Review**
   - Add support for new programming languages
   - Implement custom analysis rules

3. **Integration Features**
   - Add webhook endpoints
   - Integrate with external APIs

4. **UI Development**
   - Build a simple web interface
   - Add real-time chat features

### 🌐 **Share and Contribute**

- **Fork the repository** and build your own features
- **Open issues** for bugs or feature requests
- **Submit pull requests** with improvements
- **Share your experience** with the community

## 🎉 You're Ready!

**🚀 Congratulations!** You now have a fully functional AI-powered technical assistant running in your development environment. 

**What you've accomplished:**
✅ Enterprise-grade AI application running  
✅ REST APIs for technical consultation  
✅ Code review and analysis capabilities  
✅ Development environment ready  
✅ Path to production deployment  

**Start building amazing AI applications! 🎯**

---

> 🔗 **Quick Links**  
> • [Main Documentation](../README.md)  
> • [Architecture Guide](architecture-guide.md)  
> • [Azure Deployment](azure-deployment.md)  
> • [Dev Container Setup](../.devcontainer/README.md)