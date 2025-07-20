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

## 🚀 Usage Examples

### Setting Up Environment Variables

```bash
# GitHub integration
export GITHUB_TOKEN="your_github_personal_access_token"

# Brave Search integration  
export BRAVE_API_KEY="your_brave_search_api_key"
```

### MCP Server Configuration

The servers are configured in `~/.config/mcp/servers.json`:

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
    }
    // ... other servers
  }
}
```

### Testing MCP Servers

```bash
# List available MCP servers
mcp list-servers

# Test GitHub server
mcp inspect github

# Test filesystem server
mcp inspect filesystem
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
# Add to your shell profile (~/.bashrc or ~/.zshrc)
export GITHUB_TOKEN="your_token_here"
export BRAVE_API_KEY="your_api_key_here"
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
- [Brave Search API](https://api.search.brave.com/)

## 🔄 Integration with VibeCode Studio

The MCP servers integrate seamlessly with VibeCode Studio's Agent Core:

1. **Enhanced Context**: AI agents can access project files, repository history, and external knowledge
2. **Intelligent Code Generation**: Real-time access to documentation and best practices
3. **Database Operations**: Direct SQL query generation and execution
4. **Persistent Memory**: Conversation context across development sessions

This creates a truly intelligent development environment where AI can understand your project comprehensively and provide contextually relevant assistance.