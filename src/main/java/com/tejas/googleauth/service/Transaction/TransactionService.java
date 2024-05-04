package com.tejas.googleauth.service.Transaction;

import java.util.List;

import com.tejas.googleauth.collection.Transactions.Transaction;

import reactor.core.publisher.Flux;

public interface TransactionService {
    public List<Transaction> getAllByFilters(int skip, int limit);

    public Transaction createOne(Transaction entity);

    public void deleteOne(String id);
}
