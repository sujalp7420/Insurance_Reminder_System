package com.InsRem.dto;

import com.InsRem.entity.PolicyType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PolicyRequest {

    private String customerName;

    private String customerEmail;

    private String customerMobile;

    private PolicyType policyType;

    private String companyName;

    private String policyNumber;

    private LocalDate startDate;

    private LocalDate expiryDate;

    private BigDecimal premiumAmount;

    private String notes;
}