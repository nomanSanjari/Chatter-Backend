package com.NomanSanjari.Chatter.configurations;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfig {

    /**
     * Gets the required connection string from application.properties
     * mongo.db.connectionString
     */
    @Value("${mongo.db.connectionString}")
    private String connectionString;

    /**
     * @return MongoClient instance for Read/Write operations to/from the MongoDB database
     */
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(connectionString);
    }
}
