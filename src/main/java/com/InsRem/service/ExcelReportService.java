package com.InsRem.service;

import jakarta.servlet.http.HttpServletResponse;

public interface ExcelReportService {

    void exportPolicies(
            HttpServletResponse response)
            throws Exception;
}