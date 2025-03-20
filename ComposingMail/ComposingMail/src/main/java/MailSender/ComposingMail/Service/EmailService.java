package MailSender.ComposingMail.Service;
import MailSender.ComposingMail.Configuration.MailConfig;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.List;
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final MailConfig mailConfig;  // ✅ Inject MailConfig

    @Autowired
    public EmailService(JavaMailSender mailSender, MailConfig mailConfig) {
        this.mailSender = mailSender;
        this.mailConfig = mailConfig;
    }

    public void sendEmailWithAttachment(String to, String subject, String text, String attachmentPath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // ✅ Ensure "From" address is valid
        String fromAddress = mailConfig.getUsername();
        if (fromAddress == null || fromAddress.isEmpty()) {
            throw new IllegalArgumentException("From address must not be null or empty");
        }

        helper.setFrom(fromAddress);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        // ✅ Attach file if provided
        if (attachmentPath != null && !attachmentPath.trim().isEmpty()) {
            File file = new File(attachmentPath);
            if (file.exists()) {
                FileSystemResource fileResource = new FileSystemResource(file);
                helper.addAttachment(file.getName(), fileResource);
            } else {
                throw new IllegalArgumentException("Attachment file not found: " + attachmentPath);
            }
        }

        mailSender.send(message);
    }
    public void sendEmailToMultipleRecipients(List<String> toAddresses, String subject, String text, String attachmentPath) throws MessagingException {
        if (toAddresses == null || toAddresses.isEmpty()) {
            throw new IllegalArgumentException("At least one recipient must be provided.");
        }

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String fromAddress = mailConfig.getUsername();
        if (fromAddress == null || fromAddress.isEmpty()) {
            throw new IllegalArgumentException("From address must not be null or empty");
        }

        helper.setFrom(fromAddress);
        helper.setTo(toAddresses.toArray(new String[0])); // Convert list to array
        helper.setSubject(subject);
        helper.setText(text);

        // ✅ Attach file if provided
        if (attachmentPath != null && !attachmentPath.trim().isEmpty()) {
            File file = new File(attachmentPath);
            if (file.exists()) {
                FileSystemResource fileResource = new FileSystemResource(file);
                helper.addAttachment(file.getName(), fileResource);
            } else {
                throw new IllegalArgumentException("Attachment file not found: " + attachmentPath);
            }
        }

        mailSender.send(message);
    }
    public void sendEmailWithCC(List<String> to, List<String> cc, String subject, String text, String attachmentPath)
            throws MessagingException {
        sendEmail(to, cc, subject, text, attachmentPath);
    }

    // ✅ General method for sending email
    private void sendEmail(List<String> to, List<String> cc, String subject, String text, String attachmentPath)
            throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to.toArray(new String[0])); // Convert List to array
        if (cc != null && !cc.isEmpty()) {
            helper.setCc(cc.toArray(new String[0]));
        }
        helper.setSubject(subject);
        helper.setText(text);
        helper.setFrom(mailConfig.getUsername()); // Fetch sender email dynamically

        // Attach file if available
        if (attachmentPath != null && !attachmentPath.isEmpty()) {
            File file = new File(attachmentPath);
            if (file.exists()) {
                FileSystemResource fileResource = new FileSystemResource(file);
                helper.addAttachment(file.getName(), fileResource);
            }
        }

        mailSender.send(message);
    }
}
