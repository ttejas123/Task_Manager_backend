package com.tejas.googleauth.repository.GeneralLogging;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.tejas.googleauth.collection.*;

@Repository
public interface AuditLogRepository extends MongoRepository<AuditLog, String> {

} 