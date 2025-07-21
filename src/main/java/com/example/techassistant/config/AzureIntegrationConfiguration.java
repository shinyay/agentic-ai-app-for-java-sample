package com.example.techassistant.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.search.documents.SearchClient;
import com.azure.search.documents.SearchClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureIntegrationConfiguration {

    @Value("${techknowledge.storage.azure.blob.connection-string}")
    private String blobConnectionString;

    @Value("${techknowledge.storage.azure.blob.container-name}")
    private String containerName;

    @Value("${techknowledge.vector-store.azure.search.service-name}")
    private String searchServiceName;

    @Value("${techknowledge.vector-store.azure.search.api-key}")
    private String searchApiKey;

    @Value("${techknowledge.vector-store.azure.search.index-name}")
    private String searchIndexName;

    @Bean
    public BlobServiceClient blobServiceClient() {
        return new BlobServiceClientBuilder()
                .connectionString(blobConnectionString)
                .buildClient();
    }

    @Bean
    public SearchClient searchClient() {
        String endpoint = String.format("https://%s.search.windows.net", searchServiceName);
        return new SearchClientBuilder()
                .endpoint(endpoint)
                .credential(new com.azure.core.credential.AzureKeyCredential(searchApiKey))
                .indexName(searchIndexName)
                .buildClient();
    }
}