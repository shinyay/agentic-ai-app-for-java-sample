#!/bin/bash

# MCP (Model Context Protocol) Servers Setup for VibeCode Studio
# This script installs and configures MCP servers suitable for Java/Spring Boot development

echo "🔧 Setting up MCP Servers for VibeCode Studio..."

# Ensure npm global directory exists
mkdir -p /usr/local/share/npm-global
npm config set prefix '/usr/local/share/npm-global'

# Install MCP CLI
echo "📦 Installing MCP CLI..."
npm install -g @modelcontextprotocol/cli

# Install GitHub MCP Server for repository operations
echo "📦 Installing GitHub MCP Server..."
npm install -g @modelcontextprotocol/server-github

# Install File System MCP Server for project structure management
echo "📦 Installing File System MCP Server..."
npm install -g @modelcontextprotocol/server-filesystem

# Install Brave Search MCP Server for web search capabilities
echo "📦 Installing Brave Search MCP Server..."
npm install -g @modelcontextprotocol/server-brave-search

# Install SQLite MCP Server for database operations (can work with H2 as well)
echo "📦 Installing SQLite MCP Server..."
npm install -g @modelcontextprotocol/server-sqlite

# Install Memory MCP Server for context management
echo "📦 Installing Memory MCP Server..."
npm install -g @modelcontextprotocol/server-memory

# Install Azure MCP Server for Azure resource management
echo "📦 Installing Azure MCP Server..."
npm install -g @azure/mcp-server-azure

# Create MCP configuration directory
echo "📁 Creating MCP configuration..."
mkdir -p /home/vscode/.config/mcp

# Create MCP server configuration
cat > /home/vscode/.config/mcp/servers.json << 'EOF'
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
    "brave-search": {
      "command": "npx", 
      "args": ["@modelcontextprotocol/server-brave-search"],
      "env": {
        "BRAVE_API_KEY": "${BRAVE_API_KEY}"
      }
    },
    "sqlite": {
      "command": "npx",
      "args": ["@modelcontextprotocol/server-sqlite", "--db-path", "/workspaces/agentic-ai-app-for-java-sample/data"]
    },
    "memory": {
      "command": "npx",
      "args": ["@modelcontextprotocol/server-memory"]
    },
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
EOF

# Create data directory for SQLite MCP server
mkdir -p /workspaces/agentic-ai-app-for-java-sample/data

# Set proper ownership
chown -R vscode:vscode /home/vscode/.config
chown -R vscode:vscode /workspaces/agentic-ai-app-for-java-sample/data

echo "✅ MCP Servers setup completed!"
echo ""
echo "📋 Available MCP Servers:"
echo "  • GitHub - Repository operations and code management"
echo "  • FileSystem - Project structure and file operations"
echo "  • Brave Search - Web search capabilities for research"
echo "  • SQLite - Database operations and queries"
echo "  • Memory - Context management and conversation history"
echo "  • Azure - Azure resource management and operations"
echo ""
echo "🔑 Environment Variables (set these for full functionality):"
echo "  • GITHUB_TOKEN - GitHub Personal Access Token"
echo "  • BRAVE_API_KEY - Brave Search API Key"
echo "  • AZURE_CLIENT_ID - Azure Application (client) ID"
echo "  • AZURE_CLIENT_SECRET - Azure Client Secret"
echo "  • AZURE_TENANT_ID - Azure Directory (tenant) ID"
echo "  • AZURE_SUBSCRIPTION_ID - Azure Subscription ID"
echo ""
echo "📖 Configuration file: ~/.config/mcp/servers.json"