package com.NomanSanjari.Chatter.repositories;

import com.NomanSanjari.Chatter.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SessionRepository extends ReactiveMongoRepository<User, String> {

}
