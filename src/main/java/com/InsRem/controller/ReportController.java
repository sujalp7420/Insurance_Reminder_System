package com.InsRem.controller;

import com.InsRem.service.ExcelReportService;
import com.InsRem.service.PdfReportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ExcelReportService excelService;
    private final PdfReportService pdfService;

    @GetMapping("/report/excel")
    public void exportExcel(
            HttpServletResponse response)
            throws Exception {

        response.setContentType(
                "application/octet-stream");

        response.setHeader(
                "Content-Disposition",
                "attachment;filename=policies.xlsx");

        excelService.exportPolicies(
                response);
    }

    @GetMapping("/report/pdf")
    public void exportPdf(
            HttpServletResponse response)
            throws Exception {

        response.setContentType(
                "application/pdf");

        response.setHeader(
                "Content-Disposition",
                "attachment;filename=policies.pdf");

        pdfService.exportPdf(
                response);
    }
}