package com.example.CronZipMailScheduler;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${recipient.email}")
    private String recipientEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmailWithAttachment(String filePath) throws MessagingException {
        System.out.println("📧 Attempting to send email with attachment...");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipientEmail);
        helper.setSubject("Automated File Transfer");
        helper.setText("Please find the attached zipped file.");

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("❌ File not found: " + filePath);
            return;
        }

        FileSystemResource fileResource = new FileSystemResource(file);
        helper.addAttachment(fileResource.getFilename(), fileResource);

        mailSender.send(message);

        System.out.println("✅ Email sent successfully to: " + recipientEmail);
    }

}
