package MailSender.ComposingMail.Controller;
import MailSender.ComposingMail.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String text,
            @RequestParam(required = false) MultipartFile attachment) {

        try {
            String attachmentPath = null;

            // Save file temporarily if provided
            if (attachment != null && !attachment.isEmpty()) {
                File tempFile = File.createTempFile("attachment-", attachment.getOriginalFilename());
                attachment.transferTo(tempFile);
                attachmentPath = tempFile.getAbsolutePath();
            }

            emailService.sendEmailWithAttachment(to, subject, text, attachmentPath);

            return ResponseEntity.ok("Email sent successfully!");

        } catch (MessagingException | IOException e) {
            return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
        }
    }
    // ✅ New API for Multiple Recipients
    @PostMapping("/send-multiple")
    public String sendEmailToMultiple(
            @RequestParam("to") List<String> to,
            @RequestParam String subject,
            @RequestParam String text,
            @RequestParam(required = false) MultipartFile attachment) {

        try {
            String attachmentPath = saveAttachment(attachment);
            emailService.sendEmailToMultipleRecipients(to, subject, text, attachmentPath);
            return "Email sent successfully to: " + String.join(", ", to);
        } catch (MessagingException | IOException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }

    // ✅ Utility method to save attachment temporarily
    private String saveAttachment(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        File tempFile = File.createTempFile("email-attachment-", file.getOriginalFilename());
        file.transferTo(tempFile);
        return tempFile.getAbsolutePath();
    }
    @PostMapping("/send-multiple-with-cc")
    public String sendEmailWithCC(
            @RequestParam("to") List<String> to,
            @RequestParam(value = "cc", required = false) List<String> cc,  // Optional CC list
            @RequestParam String subject,
            @RequestParam String text,
            @RequestParam(required = false) MultipartFile attachment) {

        try {
            String attachmentPath = saveAttachment(attachment);
            emailService.sendEmailWithCC(to, cc, subject, text, attachmentPath);
            return "Email sent successfully to: " + String.join(", ", to) +
                    (cc != null ? " with CC: " + String.join(", ", cc) : "");
        } catch (MessagingException | IOException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }

}
