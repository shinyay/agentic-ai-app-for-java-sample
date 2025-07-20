# VibeCode Studio - Java Development Container
FROM mcr.microsoft.com/devcontainers/java:1-17-bullseye

# Install system dependencies
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends \
        curl \
        wget \
        git \
        ca-certificates \
        gnupg \
        lsb-release \
        software-properties-common \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Install Azure CLI
RUN curl -sL https://aka.ms/InstallAzureCLIDeb | bash

# Install GitHub CLI
RUN curl -fsSL https://cli.github.com/packages/githubcli-archive-keyring.gpg | dd of=/usr/share/keyrings/githubcli-archive-keyring.gpg \
    && chmod go+r /usr/share/keyrings/githubcli-archive-keyring.gpg \
    && echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/githubcli-archive-keyring.gpg] https://cli.github.com/packages stable main" | tee /etc/apt/sources.list.d/github-cli.list > /dev/null \
    && apt-get update \
    && apt-get install gh -y

# Install Node.js 20
RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash - \
    && apt-get install -y nodejs

# Configure npm global directory
RUN mkdir -p /usr/local/share/npm-global \
    && npm config set prefix '/usr/local/share/npm-global' \
    && echo 'export PATH=/usr/local/share/npm-global/bin:$PATH' >> /home/vscode/.bashrc

# Install Maven 3.9 (via SDKMAN)
USER vscode
RUN bash -c "source /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven 3.9.6"

# Set up environment variables
ENV JAVA_HOME=/usr/local/sdkman/candidates/java/current
ENV MAVEN_HOME=/usr/local/sdkman/candidates/maven/current
ENV NODE_PATH=/usr/local/share/npm-global/lib/node_modules
ENV PATH=$PATH:/usr/local/share/npm-global/bin

# Install MCP CLI and servers
RUN npm install -g @modelcontextprotocol/cli \
    && npm install -g @modelcontextprotocol/server-github \
    && npm install -g @modelcontextprotocol/server-filesystem \
    && npm install -g @modelcontextprotocol/server-brave-search \
    && npm install -g @modelcontextprotocol/server-sqlite \
    && npm install -g @modelcontextprotocol/server-memory

# Create MCP configuration directory
RUN mkdir -p /home/vscode/.config/mcp

# Copy and run MCP setup script
COPY setup-mcp.sh /tmp/setup-mcp.sh
USER root
RUN chmod +x /tmp/setup-mcp.sh
USER vscode

# Set default working directory
WORKDIR /workspaces/agentic-ai-app-for-java-sample

# Keep container running
CMD ["sleep", "infinity"]