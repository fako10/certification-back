package com.example.ceertifications.dto;

import com.example.ceertifications.entities.Certification;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class CheckoutPayment {


    private String name;
        private Long certificationId;
    //  currency like usd, eur ...
    private String currency;
    // our success and cancel url stripe will redirect to this links
    private String successUrl;
    private String cancelUrl;
    private long amount;
    private LocalDateTime payementDate;
    private long quantity;

}
