package com.tejas.googleauth.repository.GoogleAuth;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.tejas.googleauth.collection.GoogleAuth;

@Repository
@EnableMongoRepositories
public interface GoogleAuthRepository extends MongoRepository<GoogleAuth, String> {
    
    @Query(value = "{ 'email': ?0 }")
    GoogleAuth findByEmail(String email);

    @Aggregation(pipeline = {"{ '$skip': ?0}, { '$limit': ?1}"})
    List<GoogleAuth> findAllByFilter(int skip, int limit);

}