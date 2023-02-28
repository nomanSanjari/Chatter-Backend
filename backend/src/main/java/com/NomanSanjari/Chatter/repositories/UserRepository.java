package com.NomanSanjari.Chatter.repositories;

import com.NomanSanjari.Chatter.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
