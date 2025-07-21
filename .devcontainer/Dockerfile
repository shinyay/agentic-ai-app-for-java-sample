FROM openjdk:17-jdk-slim

# Install essential packages including packages needed for Azure CLI
RUN apt-get update && apt-get install -y \
    curl \
    wget \
    git \
    vim \
    nano \
    unzip \
    ca-certificates \
    gnupg \
    lsb-release \
    software-properties-common \
    apt-transport-https \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Install Maven
ARG MAVEN_VERSION=3.9.6
RUN wget https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    && tar -xzf apache-maven-${MAVEN_VERSION}-bin.tar.gz -C /opt \
    && ln -s /opt/apache-maven-${MAVEN_VERSION} /opt/maven \
    && rm apache-maven-${MAVEN_VERSION}-bin.tar.gz

# Install Azure CLI
RUN curl -sL https://aka.ms/InstallAzureCLIDeb | bash

# Install Azure Developer CLI (azd)
RUN curl -fsSL https://aka.ms/install-azd.sh | bash -s -- --install-folder /usr/local/bin

# Set Maven environment variables and ensure Azure CLI tools are in PATH
ENV MAVEN_HOME=/opt/maven
ENV PATH="${MAVEN_HOME}/bin:/usr/local/bin:${PATH}"

# Create workspace directory
WORKDIR /workspace

# Create non-root user for development
RUN groupadd --gid 1000 vscode \
    && useradd --uid 1000 --gid vscode --shell /bin/bash --create-home vscode \
    && chown -R vscode:vscode /workspace

USER vscode

# Set up Maven settings
RUN mkdir -p /home/vscode/.m2