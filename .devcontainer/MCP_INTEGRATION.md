# MCP (Model Context Protocol) Integration

This document explains the MCP servers integrated into the VibeCode Studio development environment to enhance AI-powered development capabilities.

## 🎯 What is MCP?

Model Context Protocol (MCP) is an open standard that enables seamless communication between AI tools and various data sources, services, and development tools. It allows AI assistants to access and interact with external systems in a standardized way.

## 🔧 Installed MCP Servers

The Dev Container includes the following MCP servers optimized for Java/Spring Boot development:

### 1. **GitHub MCP Server** (`@modelcontextprotocol/server-github`)
- **Purpose**: Repository operations and code management
- **Capabilities**:
  - Read and analyze repository structure
  - Access commit history and pull requests
  - Search code across repositories
  - Manage issues and discussions
- **Environment**: Requires `GITHUB_TOKEN` for authentication

### 2. **FileSystem MCP Server** (`@modelcontextprotocol/server-filesystem`)
- **Purpose**: Project structure and file operations
- **Capabilities**:
  - Read and write project files
  - Navigate directory structures
  - Analyze code patterns and dependencies
  - Monitor file changes
- **Scope**: Limited to `/workspaces/agentic-ai-app-for-java-sample`

### 3. **Brave Search MCP Server** (`@modelcontextprotocol/server-brave-search`)
- **Purpose**: Web search capabilities for research
- **Capabilities**:
  - Search for documentation and tutorials
  - Find code examples and best practices
  - Research new technologies and libraries
  - Access real-time information
- **Environment**: Requires `BRAVE_API_KEY` for API access

### 4. **SQLite MCP Server** (`@modelcontextprotocol/server-sqlite`)
- **Purpose**: Database operations and queries
- **Capabilities**:
  - Execute SQL queries
  - Analyze database schemas
  - Generate test data
  - Database migration assistance
- **Storage**: Uses `/workspaces/agentic-ai-app-for-java-sample/data` directory

### 5. **Memory MCP Server** (`@modelcontextprotocol/server-memory`)
- **Purpose**: Context management and conversation history
- **Capabilities**:
  - Store conversation context
  - Remember project-specific information
  - Maintain development session state
  - Cross-session knowledge retention

### 6. **Azure MCP Server** (`@azure/mcp-server-azure`)
- **Purpose**: Azure resource management and operations
- **Capabilities**:
  - Manage Azure resources (VMs, databases, storage)
  - Deploy applications to Azure services
  - Monitor Azure resource health and costs
  - Access Azure AI services and APIs
  - Integration with Azure DevOps and GitHub Actions
- **Environment**: Requires Azure service principal credentials

## 🚀 Usage Examples

### Setting Up Environment Variables with Azure CLI

The recommended approach is to use Azure CLI for authentication and configuration:

```bash
# Step 1: Authenticate with Azure CLI
bash .devcontainer/azure-auth.sh login

# Step 2: Set up Azure environment
bash .devcontainer/azure-auth.sh env
source azure-env.sh

# Step 3: Create Azure resources (optional)
bash .devcontainer/setup-azure.sh
```

### Manual Environment Variables (Alternative)

```bash
# GitHub integration
export GITHUB_TOKEN="your_github_personal_access_token"

# Brave Search integration  
export BRAVE_API_KEY="your_brave_search_api_key"

# Azure integration - Option 1: Service Principal
export AZURE_CLIENT_ID="your_azure_application_client_id"
export AZURE_CLIENT_SECRET="your_azure_client_secret"
export AZURE_TENANT_ID="your_azure_directory_tenant_id"
export AZURE_SUBSCRIPTION_ID="your_azure_subscription_id"

# Azure integration - Option 2: Azure CLI (Recommended)
export AZURE_CLI_AUTH="true"
export AZURE_TENANT_ID=$(az account show --query tenantId -o tsv)
export AZURE_SUBSCRIPTION_ID=$(az account show --query id -o tsv)
```

### MCP Server Configuration

The servers can be configured using Azure CLI authentication or traditional service principal:

#### Option 1: Azure CLI Authentication (Recommended)
```json
{
  "servers": {
    "azure": {
      "command": "npx",
      "args": ["@azure/mcp-server-azure"],
      "env": {
        "AZURE_CLI_AUTH": "true",
        "AZURE_TENANT_ID": "${AZURE_TENANT_ID}",
        "AZURE_SUBSCRIPTION_ID": "${AZURE_SUBSCRIPTION_ID}"
      }
    }
  }
}
```

#### Option 2: Service Principal Authentication
```json
{
  "servers": {
    "azure": {
      "command": "npx",
      "args": ["@azure/mcp-server-azure"],
      "env": {
        "AZURE_CLIENT_ID": "${AZURE_CLIENT_ID}",
        "AZURE_CLIENT_SECRET": "${AZURE_CLIENT_SECRET}",
        "AZURE_TENANT_ID": "${AZURE_TENANT_ID}",
        "AZURE_SUBSCRIPTION_ID": "${AZURE_SUBSCRIPTION_ID}"
      }
    }
  }
}
```

Complete configuration in `~/.config/mcp/servers.json`:
```json
{
  "servers": {
    "github": {
      "command": "npx",
      "args": ["@modelcontextprotocol/server-github"],
      "env": {
        "GITHUB_PERSONAL_ACCESS_TOKEN": "${GITHUB_TOKEN}"
      }
    },
    "filesystem": {
      "command": "npx",
      "args": ["@modelcontextprotocol/server-filesystem", "/workspaces/agentic-ai-app-for-java-sample"]
    },
    "azure": {
      "command": "npx",
      "args": ["@azure/mcp-server-azure"],
      "env": {
        "AZURE_CLI_AUTH": "true",
        "AZURE_TENANT_ID": "${AZURE_TENANT_ID}",
        "AZURE_SUBSCRIPTION_ID": "${AZURE_SUBSCRIPTION_ID}"
      }
    }
  }
}
```

### Testing MCP Servers

```bash
# Configure Azure authentication
bash .devcontainer/azure-auth.sh mcp

# List available MCP servers
mcp list-servers

# Test GitHub server
mcp inspect github

# Test filesystem server
mcp inspect filesystem

# Test Azure server (requires Azure CLI login)
mcp inspect azure
```

## 🎨 AI Development Scenarios

### 1. **Code Analysis and Refactoring**
```bash
# AI can now:
# - Read your entire project structure via FileSystem MCP
# - Access repository history via GitHub MCP  
# - Search for best practices via Brave Search MCP
# - Remember previous refactoring decisions via Memory MCP
```

### 2. **Database Development**
```bash
# AI can now:
# - Generate and execute SQL queries via SQLite MCP
# - Analyze your database schema
# - Create migration scripts
# - Generate test data
```

### 3. **Research and Documentation**
```bash
# AI can now:
# - Search for Java/Spring Boot documentation
# - Find relevant code examples online
# - Research new libraries and frameworks
# - Access real-time technology information
```

### 4. **Azure Cloud Operations**
```bash
# AI can now:
# - Deploy Spring Boot applications to Azure App Service
# - Manage Azure databases (PostgreSQL, SQL Server)
# - Configure Azure AI services integration
# - Monitor Azure resources and costs
# - Set up CI/CD pipelines with Azure DevOps
```

## 🔒 Security Considerations

- **Filesystem Access**: Limited to project directory only
- **GitHub Access**: Requires personal access token with minimal permissions
- **Search Access**: Uses Brave Search API (privacy-focused)
- **Local Storage**: SQLite and Memory servers store data locally

## 🛠️ Troubleshooting

### MCP Server Not Starting
```bash
# Check if Node.js is available
node --version

# Verify MCP CLI installation
mcp --version

# Check server configuration
cat ~/.config/mcp/servers.json
```

### Environment Variables Not Set
```bash
# Using Azure CLI (Recommended)
bash .devcontainer/azure-auth.sh login
bash .devcontainer/azure-auth.sh env
source azure-env.sh

# Manual setup - Add to your shell profile (~/.bashrc or ~/.zshrc)
export GITHUB_TOKEN="your_token_here"
export BRAVE_API_KEY="your_api_key_here"

# Azure CLI authentication (recommended)
export AZURE_CLI_AUTH="true"
export AZURE_TENANT_ID=$(az account show --query tenantId -o tsv)
export AZURE_SUBSCRIPTION_ID=$(az account show --query id -o tsv)

# Alternative: Azure Service Principal 
export AZURE_CLIENT_ID="your_client_id_here"
export AZURE_CLIENT_SECRET="your_client_secret_here"
export AZURE_TENANT_ID="your_tenant_id_here"
export AZURE_SUBSCRIPTION_ID="your_subscription_id_here"
```

### Permission Issues
```bash
# Fix ownership if needed
sudo chown -R vscode:vscode ~/.config/mcp
sudo chown -R vscode:vscode /workspaces/agentic-ai-app-for-java-sample/data
```

## 📚 Additional Resources

- [MCP Specification](https://modelcontextprotocol.io/)
- [GitHub MCP Server](https://github.com/modelcontextprotocol/servers/tree/main/src/github)
- [FileSystem MCP Server](https://github.com/modelcontextprotocol/servers/tree/main/src/filesystem)
- [Azure MCP Server](https://github.com/Azure/azure-mcp)
- [Brave Search API](https://api.search.brave.com/)
- [Azure CLI Documentation](https://docs.microsoft.com/en-us/cli/azure/)
- [Azure Service Principal Setup](https://docs.microsoft.com/en-us/azure/active-directory/develop/howto-create-service-principal-portal)

## 🔄 Integration with VibeCode Studio

The MCP servers integrate seamlessly with VibeCode Studio's Agent Core:

1. **Enhanced Context**: AI agents can access project files, repository history, and external knowledge
2. **Intelligent Code Generation**: Real-time access to documentation and best practices
3. **Database Operations**: Direct SQL query generation and execution
4. **Persistent Memory**: Conversation context across development sessions
5. **Azure Cloud Integration**: Seamless deployment and management of Azure resources via CLI
6. **Simplified Authentication**: Use Azure CLI login instead of managing service principal credentials

### Azure CLI Benefits
- **Simplified Setup**: One-command authentication with `az login`
- **Automatic Credential Management**: No need to manage secrets manually
- **Multi-Factor Authentication**: Supports Azure MFA and conditional access
- **Role-Based Access**: Uses your existing Azure permissions
- **Secure by Default**: Credentials are stored securely by Azure CLI

This creates a truly intelligent development environment where AI can understand your project comprehensively, provide contextually relevant assistance, and deploy applications directly to Azure cloud services using your existing Azure credentials.