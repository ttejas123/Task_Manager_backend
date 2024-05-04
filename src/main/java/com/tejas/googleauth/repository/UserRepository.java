package com.tejas.googleauth.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.tejas.googleauth.collection.User;
import com.tejas.googleauth.collection.UserWithAddedField;

import java.util.List;

@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User, String> {
   
    @Query(value = "{ 'age': { '$gt': ?0, '$lt': ?1 } }")
    List<User> getUsersByAgeBetween(Integer min, Integer max);
  
    @Aggregation(pipeline = {"{ '$addFields': {'test': '$type'}}}"})
    List<UserWithAddedField> getUsersByAddingNewField();
}
