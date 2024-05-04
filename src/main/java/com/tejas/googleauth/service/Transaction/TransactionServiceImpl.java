package com.tejas.googleauth.service.Transaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.tejas.googleauth.collection.Transactions.Transaction;
import com.tejas.googleauth.repository.Transaction.TransactionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    
    @Override
    public List<Transaction> getAllByFilters(int skip, int limit) {
        try {
            return transactionRepository.getAllByFilters(skip, limit);
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    @Override
    public Transaction createOne(Transaction entity) {
        try {
            return transactionRepository.save(entity);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteOne(String id) {
        this.transactionRepository.deleteById(id);
    }
    
}
