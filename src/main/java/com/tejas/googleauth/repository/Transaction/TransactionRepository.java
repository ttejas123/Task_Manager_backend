package com.tejas.googleauth.repository.Transaction;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tejas.googleauth.collection.Transactions.Transaction;

import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    @Aggregation(pipeline = {"{ '$skip': ?0}, { '$limit': ?1}"})
    List<Transaction> getAllByFilters(int skip, int limit);
    
}
