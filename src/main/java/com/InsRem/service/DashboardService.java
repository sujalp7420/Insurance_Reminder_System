package com.InsRem.service;

public interface DashboardService {

    long totalPolicies();

    long activePolicies();

    long expiredPolicies();

    long expiringSoon();

    long renewedPolicies();
}