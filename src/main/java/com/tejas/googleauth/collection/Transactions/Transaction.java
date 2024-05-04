package com.tejas.googleauth.collection.Transactions;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.auto.value.AutoValue.Builder;

import lombok.Data;

@Data
@Builder
@Document(collection = "transaction")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {
    @Id
    String _id;
    
    String amount;
    String description;
    PaymentType payment_type;
    TransactionType transaction_type;
    Date date_time;
    int liked;
    
    @CreatedBy
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;
//  String currency: Currency = Currency.getInstance("USD")
}
