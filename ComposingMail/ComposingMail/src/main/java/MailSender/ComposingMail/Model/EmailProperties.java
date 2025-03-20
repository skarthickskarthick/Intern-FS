package MailSender.ComposingMail.Model;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@ConfigurationProperties(prefix = "spring.mail")  // Auto-loads properties with 'spring.mail'
public class EmailProperties {

    private String host;
    private int port;
    private String username;
    private String password;
    private String from;
    private final Properties properties = new Properties();

    // Getters and Setters (Lombok @Data can be used for brevity)
    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public Properties getProperties() { return properties; }
}

