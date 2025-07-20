# VibeCode Studio - Java Development Container
FROM mcr.microsoft.com/devcontainers/base:1-bullseye

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
        zip \
        unzip \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Install SDKMAN
USER vscode
RUN curl -s "https://get.sdkman.io" | bash

# Install Azure CLI and GitHub CLI
USER root
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

# Install Java 24 and Maven via SDKMAN
USER vscode
RUN bash -c "source /home/vscode/.sdkman/bin/sdkman-init.sh \
    && sdk install java 24-open \
    && sdk install maven 3.9.6 \
    && sdk default java 24-open \
    && sdk default maven 3.9.6"

# Add SDKMAN, Java, and Maven to PATH for all sessions
RUN echo 'export SDKMAN_DIR="/home/vscode/.sdkman"' >> /home/vscode/.bashrc \
    && echo '[[ -s "/home/vscode/.sdkman/bin/sdkman-init.sh" ]] && source "/home/vscode/.sdkman/bin/sdkman-init.sh"' >> /home/vscode/.bashrc \
    && echo 'export JAVA_HOME="/home/vscode/.sdkman/candidates/java/current"' >> /home/vscode/.bashrc \
    && echo 'export MAVEN_HOME="/home/vscode/.sdkman/candidates/maven/current"' >> /home/vscode/.bashrc \
    && echo 'export PATH="$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH"' >> /home/vscode/.bashrc

# Set up environment variables
ENV SDKMAN_DIR=/home/vscode/.sdkman
ENV JAVA_HOME=/home/vscode/.sdkman/candidates/java/current
ENV MAVEN_HOME=/home/vscode/.sdkman/candidates/maven/current
ENV NODE_PATH=/usr/local/share/npm-global/lib/node_modules
ENV PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:/usr/local/share/npm-global/bin:$PATH

# Install MCP CLI and servers
USER root
RUN npm install -g @modelcontextprotocol/cli \
    && npm install -g @modelcontextprotocol/server-github \
    && npm install -g @modelcontextprotocol/server-filesystem \
    && npm install -g @modelcontextprotocol/server-brave-search \
    && npm install -g @modelcontextprotocol/server-sqlite \
    && npm install -g @modelcontextprotocol/server-memory \
    && npm install -g @azure/mcp-server-azure

# Create MCP configuration directory
USER vscode
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