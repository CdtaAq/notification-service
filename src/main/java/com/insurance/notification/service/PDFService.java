package com.insurance.notification.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class PDFService {
    public String generatePolicyPDF(String policyHolderName, String policyNumber) throws Exception {
        String filePath = "target/generated-pdfs/policy_" + policyNumber + ".pdf";
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        PdfWriter writer = new PdfWriter(new FileOutputStream(file));
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Auto Insurance Policy"));
        document.add(new Paragraph("Policy Holder: " + policyHolderName));
        document.add(new Paragraph("Policy Number: " + policyNumber));
        document.add(new Paragraph("Status: ACTIVE"));
        document.close();

        return filePath;
    }
}
