package com.tejas.googleauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.googleauth.collection.GoogleAuth;
import com.tejas.googleauth.core.event.Audit.AuditEventPublisher;
import com.tejas.googleauth.service.GoogleAuth.GoogleAuthService;

import reactor.core.publisher.Mono;

import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/google_auth")
public class GoogleAuthController {
    @Autowired
    GoogleAuthService googleAuthService;

    @Autowired
    AuditEventPublisher eventPublisher;

    @GetMapping
    public ResponseEntity<Mono<List<GoogleAuth>>> findAll(@RequestParam int limit, @RequestParam int page) {
        int skip = page * limit;
        Mono<List<GoogleAuth>> list = googleAuthService.findAll(skip, limit);
        eventPublisher.publishGoogleAuthEvent(list.block().size()+"", "-1");
        return ResponseEntity.status(200).body(list);
    }
    
    @PostMapping
    public ResponseEntity<Mono<GoogleAuth>> createOne(@RequestBody GoogleAuth googleAuth) {
        Mono<GoogleAuth> googleauth = googleAuthService.handleGoogleAuth(""+googleAuth.getGoogle_id_token());
        return ResponseEntity.status(201).body(googleauth);
    }
    
    @DeleteMapping
    public ResponseEntity deleteOne(
        @RequestParam(defaultValue="") String Id 
    ) {
        googleAuthService.deleteOne(Id);
        return ResponseEntity.status(204).body(null);
    }
}
