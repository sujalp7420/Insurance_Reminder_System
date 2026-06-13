package com.InsRem.service;

import jakarta.servlet.http.HttpServletResponse;

public interface PdfReportService {

    void exportPdf(
            HttpServletResponse response)
            throws Exception;
}