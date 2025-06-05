package com.insurance.notification.controller;

import com.insurance.notification.service.EmailService;
import com.insurance.notification.service.PDFService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    @Autowired
    private PDFService pdfService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-policy")
    public String sendPolicyEmail(
        @RequestParam String toEmail,
        @RequestParam String policyHolderName,
        @RequestParam String policyNumber
    ) {
        try {
            String pdfPath = pdfService.generatePolicyPDF(policyHolderName, policyNumber);
            emailService.sendEmailWithAttachment(
                toEmail,
                "Your Auto Insurance Policy",
                "Please find your attached insurance policy.",
                pdfPath
            );
            return "Email with PDF sent successfully!";
        } catch (Exception e) {
            return "Failed to send email: " + e.getMessage();
        }
    }
}
