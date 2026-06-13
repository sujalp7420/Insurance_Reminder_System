package com.InsRem.service.impl;

import com.InsRem.entity.Policy;
import com.InsRem.repository.PolicyRepository;
import com.InsRem.service.AgentService;
import com.InsRem.service.PdfReportService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfReportServiceImpl
        implements PdfReportService {

    private final PolicyRepository policyRepository;
    private final AgentService agentService;

    @Override
    public void exportPdf(
            HttpServletResponse response)
            throws Exception {

        response.setContentType("application/pdf");

        Document document =
                new Document(PageSize.A4);

        PdfWriter.getInstance(
                document,
                response.getOutputStream());

        document.open();

        Font titleFont =
                FontFactory.getFont(
                        FontFactory.HELVETICA_BOLD,
                        20);

        Paragraph title =
                new Paragraph(
                        "INSURANCE POLICY REPORT",
                        titleFont);

        title.setAlignment(
                Element.ALIGN_CENTER);

        document.add(title);

        document.add(
                new Paragraph(" "));

        document.add(
                new Paragraph(
                        "Generated On : "
                                + java.time.LocalDate.now()));

        document.add(
                new Paragraph(" "));

        PdfPTable table =
                new PdfPTable(5);

        table.setWidthPercentage(100);

        PdfPCell cell;

        cell = new PdfPCell(
                new Phrase("Customer"));
        cell.setBackgroundColor(
                new Color(52,152,219));
        table.addCell(cell);

        cell = new PdfPCell(
                new Phrase("Mobile"));
        cell.setBackgroundColor(
                new Color(52,152,219));
        table.addCell(cell);

        cell = new PdfPCell(
                new Phrase("Company"));
        cell.setBackgroundColor(
                new Color(52,152,219));
        table.addCell(cell);

        cell = new PdfPCell(
                new Phrase("Policy No"));
        cell.setBackgroundColor(
                new Color(52,152,219));
        table.addCell(cell);

        cell = new PdfPCell(
                new Phrase("Expiry Date"));
        cell.setBackgroundColor(
                new Color(52,152,219));
        table.addCell(cell);

        List<Policy> policies =
                policyRepository.findByAgent(
                        agentService.getLoggedInAgent());

        for (Policy p : policies) {

            table.addCell(
                    p.getCustomerName());

            table.addCell(
                    p.getCustomerMobile());

            table.addCell(
                    p.getCompanyName());

            table.addCell(
                    p.getPolicyNumber());

            table.addCell(
                    p.getExpiryDate()
                            .toString());
        }

        document.add(table);

        document.close();
    }
}