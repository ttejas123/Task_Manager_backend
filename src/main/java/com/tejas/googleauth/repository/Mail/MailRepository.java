package com.tejas.googleauth.repository.Mail;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.tejas.googleauth.collection.MailStruct;

@Repository
@EnableMongoRepositories
public interface MailRepository extends MongoRepository<MailStruct, String> {

    
} 