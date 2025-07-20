#!/bin/bash
# Azure CLI Authentication Helper for VibeCode Studio
# This script helps configure Azure authentication using different methods

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}🔐 Azure Authentication Helper${NC}"
echo "This script helps configure Azure authentication for VibeCode Studio"
echo ""

# Function to show authentication status
show_auth_status() {
    echo -e "${BLUE}📊 Current Authentication Status:${NC}"
    
    if az account show &> /dev/null; then
        ACCOUNT_NAME=$(az account show --query user.name -o tsv)
        SUBSCRIPTION_NAME=$(az account show --query name -o tsv)
        TENANT_ID=$(az account show --query tenantId -o tsv)
        
        echo -e "${GREEN}✅ Logged in to Azure CLI${NC}"
        echo "   Account: $ACCOUNT_NAME"
        echo "   Subscription: $SUBSCRIPTION_NAME"
        echo "   Tenant: $TENANT_ID"
    else
        echo -e "${YELLOW}⚠️  Not logged in to Azure CLI${NC}"
    fi
    echo ""
}

# Function for interactive login
interactive_login() {
    echo -e "${BLUE}🔓 Starting Interactive Login...${NC}"
    az login
    echo -e "${GREEN}✅ Login successful!${NC}"
    echo ""
    show_auth_status
}

# Function for device code login (useful in codespaces)
device_code_login() {
    echo -e "${BLUE}📱 Starting Device Code Login...${NC}"
    echo "This method is useful when running in GitHub Codespaces or containers"
    az login --use-device-code
    echo -e "${GREEN}✅ Login successful!${NC}"
    echo ""
    show_auth_status
}

# Function for service principal login
service_principal_login() {
    echo -e "${BLUE}🤖 Service Principal Login${NC}"
    echo "Please provide your service principal credentials:"
    echo ""
    
    read -p "Client ID: " CLIENT_ID
    read -s -p "Client Secret: " CLIENT_SECRET
    echo ""
    read -p "Tenant ID: " TENANT_ID
    
    az login --service-principal \
        --username "$CLIENT_ID" \
        --password "$CLIENT_SECRET" \
        --tenant "$TENANT_ID"
    
    echo -e "${GREEN}✅ Service principal login successful!${NC}"
    echo ""
    show_auth_status
}

# Function to configure MCP Azure authentication
configure_mcp_auth() {
    echo -e "${BLUE}🔧 Configuring MCP Azure Authentication...${NC}"
    
    if ! az account show &> /dev/null; then
        echo -e "${RED}❌ Please login to Azure CLI first.${NC}"
        return 1
    fi
    
    # Get current account info
    SUBSCRIPTION_ID=$(az account show --query id -o tsv)
    TENANT_ID=$(az account show --query tenantId -o tsv)
    
    # For MCP, we'll use the Azure CLI authentication method
    # which doesn't require a service principal
    
    # Update MCP configuration to use Azure CLI auth
    MCP_CONFIG="/home/vscode/.config/mcp/servers.json"
    
    if [ -f "$MCP_CONFIG" ]; then
        # Backup existing config
        cp "$MCP_CONFIG" "$MCP_CONFIG.backup"
        
        # Update Azure server configuration to use CLI auth
        cat > /tmp/mcp_azure_config.json << EOF
{
  "servers": {
    "github": {
      "command": "npx",
      "args": ["@modelcontextprotocol/server-github"],
      "env": {
        "GITHUB_PERSONAL_ACCESS_TOKEN": "\${GITHUB_TOKEN}"
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
        "BRAVE_API_KEY": "\${BRAVE_API_KEY}"
      }
    },
    "sqlite": {
      "command": "npx",
      "args": ["@modelcontextprotocol/server-sqlite", "/workspaces/agentic-ai-app-for-java-sample/data/development.db"]
    },
    "memory": {
      "command": "npx",
      "args": ["@modelcontextprotocol/server-memory"]
    },
    "azure": {
      "command": "npx",
      "args": ["@azure/mcp-server-azure"],
      "env": {
        "AZURE_TENANT_ID": "$TENANT_ID",
        "AZURE_SUBSCRIPTION_ID": "$SUBSCRIPTION_ID",
        "AZURE_CLI_AUTH": "true"
      }
    }
  }
}
EOF
        
        cp /tmp/mcp_azure_config.json "$MCP_CONFIG"
        echo -e "${GREEN}✅ MCP Azure configuration updated${NC}"
        echo "   Using Azure CLI authentication (no service principal needed)"
        echo "   Tenant ID: $TENANT_ID"
        echo "   Subscription ID: $SUBSCRIPTION_ID"
    else
        echo -e "${YELLOW}⚠️  MCP configuration file not found at $MCP_CONFIG${NC}"
        echo "   Run the MCP setup script first: bash .devcontainer/setup-mcp.sh"
    fi
    echo ""
}

# Function to set up environment variables
setup_environment() {
    echo -e "${BLUE}🌍 Setting up Environment Variables...${NC}"
    
    if ! az account show &> /dev/null; then
        echo -e "${RED}❌ Please login to Azure CLI first.${NC}"
        return 1
    fi
    
    # Get Azure account information
    SUBSCRIPTION_ID=$(az account show --query id -o tsv)
    TENANT_ID=$(az account show --query tenantId -o tsv)
    USER_NAME=$(az account show --query user.name -o tsv)
    
    # Create environment setup script
    ENV_SCRIPT="/workspaces/agentic-ai-app-for-java-sample/azure-env.sh"
    
    cat > $ENV_SCRIPT << EOF
#!/bin/bash
# Azure Environment Variables (Generated by azure-auth.sh)
# Generated on: $(date)
# Source this file to set up your environment: source azure-env.sh

echo "🔧 Setting up Azure environment variables..."

# Azure CLI Authentication
export AZURE_SUBSCRIPTION_ID="$SUBSCRIPTION_ID"
export AZURE_TENANT_ID="$TENANT_ID"

# For applications that support Azure CLI authentication
export AZURE_CLI_AUTH="true"

# Azure account info
export AZURE_USER_NAME="$USER_NAME"

echo "✅ Azure environment configured!"
echo "   Subscription: $SUBSCRIPTION_ID"
echo "   Tenant: $TENANT_ID"
echo "   User: $USER_NAME"
echo ""
echo "💡 To use Azure AI services, run: bash .devcontainer/setup-azure.sh"
EOF

    chmod +x $ENV_SCRIPT
    
    echo -e "${GREEN}✅ Environment script created: $ENV_SCRIPT${NC}"
    echo "   To load variables: source $ENV_SCRIPT"
    echo ""
}

# Function to test Azure authentication
test_authentication() {
    echo -e "${BLUE}🧪 Testing Azure Authentication...${NC}"
    
    if ! az account show &> /dev/null; then
        echo -e "${RED}❌ Not logged in to Azure CLI${NC}"
        return 1
    fi
    
    echo "Testing Azure CLI commands..."
    
    # Test basic account access
    echo -n "  Account access: "
    if az account show --output none 2>/dev/null; then
        echo -e "${GREEN}✅${NC}"
    else
        echo -e "${RED}❌${NC}"
        return 1
    fi
    
    # Test resource group listing (basic permission check)
    echo -n "  Resource groups: "
    if az group list --output none 2>/dev/null; then
        echo -e "${GREEN}✅${NC}"
    else
        echo -e "${YELLOW}⚠️ (limited permissions)${NC}"
    fi
    
    # Test cognitive services (if available)
    echo -n "  Cognitive Services: "
    if az cognitiveservices account list --output none 2>/dev/null; then
        echo -e "${GREEN}✅${NC}"
    else
        echo -e "${YELLOW}⚠️ (not available or no permissions)${NC}"
    fi
    
    echo -e "${GREEN}✅ Authentication test completed${NC}"
    echo ""
}

# Function to show help
show_help() {
    echo "Azure Authentication Helper for VibeCode Studio"
    echo ""
    echo "Usage: $0 [command]"
    echo ""
    echo "Commands:"
    echo "  status      Show current authentication status (default)"
    echo "  login       Interactive Azure CLI login"
    echo "  device      Device code login (for Codespaces/containers)"
    echo "  sp          Service principal login"
    echo "  mcp         Configure MCP Azure authentication"
    echo "  env         Set up environment variables"
    echo "  test        Test Azure authentication and permissions"
    echo "  logout      Logout from Azure CLI"
    echo "  help        Show this help message"
    echo ""
    echo "Examples:"
    echo "  $0 login    # Interactive login"
    echo "  $0 device   # Device code login for Codespaces"
    echo "  $0 mcp      # Configure MCP after login"
    echo "  $0 env      # Create environment setup script"
    echo ""
}

# Function to logout
logout() {
    echo -e "${BLUE}🔓 Logging out from Azure CLI...${NC}"
    az logout
    echo -e "${GREEN}✅ Logged out successfully${NC}"
    echo ""
}

# Main execution
case "${1:-status}" in
    "status")
        show_auth_status
        ;;
    "login")
        interactive_login
        ;;
    "device")
        device_code_login
        ;;
    "sp")
        service_principal_login
        ;;
    "mcp")
        configure_mcp_auth
        ;;
    "env")
        setup_environment
        ;;
    "test")
        test_authentication
        ;;
    "logout")
        logout
        ;;
    "help"|"-h"|"--help")
        show_help
        ;;
    *)
        echo -e "${RED}❌ Unknown command: $1${NC}"
        echo "Run '$0 help' for usage information."
        exit 1
        ;;
esac