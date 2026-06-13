package com.InsRem.service.impl;

import com.InsRem.entity.Policy;
import com.InsRem.repository.PolicyRepository;
import com.InsRem.service.AgentService;
import com.InsRem.service.ExcelReportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelReportServiceImpl
        implements ExcelReportService {

    private final PolicyRepository policyRepository;
    private final AgentService agentService;

    @Override
    public void exportPolicies(
            HttpServletResponse response)
            throws Exception {

        Workbook workbook =
                new XSSFWorkbook();

        Sheet sheet =
                workbook.createSheet("Policies");

        Row header =
                sheet.createRow(0);

        header.createCell(0)
                .setCellValue("Customer");

        header.createCell(1)
                .setCellValue("Company");

        header.createCell(2)
                .setCellValue("Policy Number");

        header.createCell(3)
                .setCellValue("Expiry Date");

        List<Policy> policies =
                policyRepository.findByAgent(
                        agentService.getLoggedInAgent());

        int rowCount = 1;

        for (Policy p : policies) {

            Row row =
                    sheet.createRow(rowCount++);

            row.createCell(0)
                    .setCellValue(
                            p.getCustomerName());

            row.createCell(1)
                    .setCellValue(
                            p.getCompanyName());

            row.createCell(2)
                    .setCellValue(
                            p.getPolicyNumber());

            row.createCell(3)
                    .setCellValue(
                            p.getExpiryDate()
                                    .toString());
        }

        workbook.write(
                response.getOutputStream());

        workbook.close();
    }
}