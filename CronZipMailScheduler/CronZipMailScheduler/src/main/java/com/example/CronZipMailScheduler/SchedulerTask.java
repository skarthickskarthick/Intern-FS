package com.example.CronZipMailScheduler;

import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class SchedulerTask {
    private final FileDownloadService fileDownloadService;
    private final ZipService zipService;
    private final EmailService emailService; // Inject EmailService

    public SchedulerTask(FileDownloadService fileDownloadService, ZipService zipService, EmailService emailService) {
        this.fileDownloadService = fileDownloadService;
        this.zipService = zipService;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 */2 * * * *") // Runs every 2 minutes
    public void executeTask() {
        try {
            System.out.println("🔄 Task Started...");

            // 1️⃣ Download file
            String filePath = fileDownloadService.downloadFile();
            System.out.println("✅ File downloaded: " + filePath);

            // 2️⃣ Zip the file
            String zipFilePath = zipService.zipFile(filePath);
            System.out.println("✅ File zipped: " + zipFilePath);

            // 3️⃣ Send email with attachment
            System.out.println("📧 Sending email with attachment...");
            emailService.sendEmailWithAttachment(zipFilePath);
            System.out.println("✅ Email sent successfully!");

            System.out.println("🎉 Task Completed Successfully!");
        } catch (IOException | MessagingException e) {
            System.err.println("❌ Error in Scheduled Task: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
