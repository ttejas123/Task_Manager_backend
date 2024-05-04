package com.tejas.googleauth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.googleauth.collection.Transactions.Transaction;
import com.tejas.googleauth.core.event.Audit.AuditEventPublisher;
import com.tejas.googleauth.service.Transaction.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService; 
    
    @Autowired
    AuditEventPublisher eventPublisher;
    
    @GetMapping
    public ResponseEntity<Mono<List<Transaction>>> getMethodName(@RequestParam int page, @RequestParam int limit) {
        int skip = page * limit;
        List<Transaction> list = transactionService.getAllByFilters(skip, limit);
        if(list == null) {
            eventPublisher.publishGoogleAuthEvent(list.size()+"", null);
            return ResponseEntity.status(500).body(Mono.just(List.of()));
        }
        eventPublisher.publishGoogleAuthEvent(list.size()+"", null);
        return ResponseEntity.status(200).body(Mono.just(list));
    }
    
    @PostMapping()
    public ResponseEntity<Transaction> createOne(@RequestBody Transaction entity) {
        Transaction tr = transactionService.createOne(entity);
        if(tr == null) {
            eventPublisher.publishGoogleAuthEvent("no data", null);
            return ResponseEntity.status(500).body(tr);
        }
        eventPublisher.publishGoogleAuthEvent(tr.toString(), "-1");
        return ResponseEntity.status(201).body(tr);
    }
    
    @DeleteMapping
    public ResponseEntity<Object> deleteOne(@RequestBody String id) {
        transactionService.deleteOne(id);
        return ResponseEntity.status(200).body(null);
    }
    
    
}
