package com.tejas.googleauth.service.GoogleAuth;
import java.util.*;
import com.tejas.googleauth.collection.GoogleAuth;

import reactor.core.publisher.Mono;


public interface GoogleAuthService {
    Mono<List<GoogleAuth>> findAll(int skip, int limit);
    
    Mono<GoogleAuth> findByEmail(String email);

    Mono<GoogleAuth> save(GoogleAuth googleAuth);
    
    void deleteOne(String id);
    
    Mono<GoogleAuth> handleGoogleAuth(String id);

} 
