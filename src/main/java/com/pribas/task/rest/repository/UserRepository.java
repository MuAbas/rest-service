package com.pribas.task.rest.repository;

import com.pribas.task.rest.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
