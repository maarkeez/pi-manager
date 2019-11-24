package com.pi.email;


import com.sun.mail.smtp.SMTPTransport;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor( onConstructor = @__( @Autowired ) )
class EmailServiceGmail implements EmailService {
  
  private final GmailConfig gmailConfig;
  private Session session;
  private SMTPTransport smtpTransport;
  
  @SneakyThrows
  @PostConstruct
  void init () {
    session = Session.getInstance(gmailConfig.buildSmtpProperties(), buildAuthenticator());
    smtpTransport = (SMTPTransport) session.getTransport("smtp");
    smtpTransport.connect(gmailConfig.getGmailSmtpServer(), gmailConfig.getEmail(), gmailConfig.getPassword());
  }
  
  @SneakyThrows
  @PreDestroy
  void tearDown () {
    smtpTransport.close();
  }
  
  private Authenticator buildAuthenticator () {
    return new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication () {
        return new PasswordAuthentication(gmailConfig.getEmail(), gmailConfig.getPassword());
      }
    };
  }
  
  @Override
  @SneakyThrows
  public void sendEmail ( Email email ) {
    log.info("Sending email: {}", email);
    try {
      
      Message msg = toMimeMessage(email);
      
      smtpTransport.sendMessage(msg, msg.getAllRecipients());
      
      log.info("Email sent, server response: {}", smtpTransport.getLastServerResponse());
      
    }
    catch (MessagingException e) {
      log.error("Could not send email: {}. Cause: ", email, e);
    }
    
  }
  
  private Message toMimeMessage ( Email email ) throws MessagingException {
    
    String toAddresses = String.join(",", email.getTo());
    
    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(gmailConfig.getEmail()));
    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddresses, false));
    msg.setSubject(email.getSubject());
    msg.setText(email.getBody());
    msg.setSentDate(Calendar.getInstance()
                            .getTime());
    return msg;
  }
  
}
