package com.NomanSanjari.Chatter.repositories;

import com.NomanSanjari.Chatter.models.Session;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SessionRepository extends ReactiveMongoRepository<Session, String> {

}
