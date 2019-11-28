package com.pi.email.service.gmail;

import static lombok.AccessLevel.PACKAGE;

import java.util.Properties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
class GmailConfig {
  
  @Getter( PACKAGE )
  private final String gmailSmtpServer = "smtp.gmail.com";
  
  @Getter( PACKAGE )
  @Value( "${email.service.username}" )
  private String email;
  
  @Getter( PACKAGE )
  @Value( "${email.service.password}" )
  private String password;
  
  Properties buildSmtpProperties () {
    Properties properties = new Properties();
    properties.setProperty("mail.transport.protocol", "smtp");
    properties.setProperty("mail.host", "smtp.gmail.com");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.port", "465");
    properties.put("mail.debug", "true");
    properties.put("mail.smtp.socketFactory.port", "465");
    properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    properties.put("mail.smtp.socketFactory.fallback", "false");
    return properties;
  }
  
}
