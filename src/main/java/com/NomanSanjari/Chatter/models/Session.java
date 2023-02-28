package com.NomanSanjari.Chatter.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sessions")
public class Session {

    @Id
    private String sessionID;
    private String sessionName;
    private User userA;
    private String userA_SDP;
    private User userB;
    private String userB_ID;

    @JsonCreator
    public Session(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public User getUserA() {
        return this.userA;
    }

    public void setUserA(User userA) {
        this.userA = userA;
    }

    public User getUserB() {
        return this.userB;
    }

    public void setUserB(User userB) {
        this.userB = userB;
    }
}
