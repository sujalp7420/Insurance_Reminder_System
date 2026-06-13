package com.InsRem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "policies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    private String customerName;

    private String customerEmail;

    private String customerMobile;

    @Column(name = "document_path")
    private String documentPath;

    @Enumerated(EnumType.STRING)
    private PolicyType policyType;

    private String companyName;

    @Column(unique = true)
    private String policyNumber;

    private LocalDate startDate;

    private LocalDate expiryDate;

    private BigDecimal premiumAmount;

    @Enumerated(EnumType.STRING)
    private PolicyStatus status;

    @Column(length = 1000)
    private String notes;
}