package com.pribas.task.rest.repository;

import com.pribas.task.rest.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/*  UserRepository handles database queries for User collection
    Has pre-implemented methods since it extends MongoRepository */
public interface UserRepository extends MongoRepository<User, String> {
}
