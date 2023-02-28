package com.NomanSanjari.Chatter.configurations;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class MongoDBConfig {

    @Value("${mongo.db.connectionString}")
    private String connectionString;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(connectionString);
    }
}
