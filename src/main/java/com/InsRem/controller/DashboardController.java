package com.InsRem.controller;

import com.InsRem.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute(
                "totalPolicies",
                dashboardService.totalPolicies());

        model.addAttribute(
                "activePolicies",
                dashboardService.activePolicies());

        model.addAttribute(
                "expiredPolicies",
                dashboardService.expiredPolicies());

        model.addAttribute(
                "renewedPolicies",
                dashboardService.renewedPolicies());

        model.addAttribute(
                "expiringSoon",
                dashboardService.expiringSoon());

        return "dashboard";
    }
}