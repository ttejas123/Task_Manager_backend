package com.tejas.googleauth.service.GoogleAuth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.tejas.googleauth.collection.GoogleAuth;
import com.tejas.googleauth.core.event.Audit.AuditEventPublisher;
import com.tejas.googleauth.repository.GoogleAuth.GoogleAuthRepository;
import com.tejas.googleauth.service.Mail.MailService;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import java.util.Collections;
import java.util.Date;

@Service
public class GoogleAuthServiceImpl implements GoogleAuthService {
    @Autowired
    private GoogleAuthRepository googleAuthRepository;
    
    @Autowired
    private MailService mailService;
    
    private final Environment env;
    public GoogleAuthServiceImpl(Environment env) {
        this.env = env;
    }
    
    
    @Autowired
    AuditEventPublisher eventPublisher;

    @Override
    public Mono<List<GoogleAuth>> findAll(int skip, int limit) {
        return Mono.just(googleAuthRepository.findAllByFilter(skip, limit));
    }

    @Override
    public Mono<GoogleAuth> findByEmail(String email) {
        GoogleAuth googleAuth = googleAuthRepository.findByEmail(email);
        if (googleAuth == null) {
            // No GoogleAuth object found for the given email
            return null;
        }
        return Mono.just(googleAuth);
    }

    @Override
    public Mono<GoogleAuth> save(GoogleAuth googleAuth) {
        return Mono.just(googleAuthRepository.save(googleAuth));
    }

    @Override
    public void deleteOne(String id ) {
        googleAuthRepository.deleteById(id);
    }

    @Override
    public Mono<GoogleAuth> handleGoogleAuth(String idTokenString) {
        try {
            JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jsonFactory)
            .setAudience(Collections.singletonList("124718215654-m36pmnlhlcfg63ig4034j7c57qpnjlg1.apps.googleusercontent.com"))
            .build();
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                Payload payload = idToken.getPayload();
                String userId = (String) payload.get("sub");
                String name = (String) payload.get("name");
                String email = (String) payload.get("email");
                String pictureUrl = (String) payload.get("picture");
                GoogleAuth googleAuth = GoogleAuth.builder()
                .google_id_token(idTokenString)
                .displayname(name)
                .user_id(userId)
                .email(email)
                .created_at(new Date())
                .updated_at(new Date())
                .build();
                GoogleAuth ifUserExist = this.findByEmail(email).block();
                if(ifUserExist == null) {
                    this.save(googleAuth);
                    eventPublisher.publishGoogleAuthEvent(googleAuth.toString(), googleAuth.get_id());
                    Mono.fromRunnable(() -> mailService.sendMail(googleAuth.getEmail(), googleAuth.getDisplayname() + " welcome aboard", "Hello, \n welcome aboard " + googleAuth.getDisplayname()))
                    .subscribeOn(Schedulers.boundedElastic())
                    .subscribe();
                    return Mono.just(googleAuth);
                } 
                Mono.fromRunnable(() -> mailService.sendMail(googleAuth.getEmail(), googleAuth.getDisplayname() + " welcome aboard", "Hello, \n welcome aboard " + googleAuth.getDisplayname()))
                    .subscribeOn(Schedulers.boundedElastic())
                    .subscribe();
                return Mono.just(ifUserExist);
            } else {
                // Handle invalid token
                return null;
            }
        } catch (Exception e) {
            // Handle exception
            return null;
        }
    }
} 