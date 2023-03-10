package com.NomanSanjari.Chatter.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String userID;
    private String username;

    @JsonCreator
    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return this.userID;
    }

    public void setId(String userID) {
        this.userID = userID;
    }
}
