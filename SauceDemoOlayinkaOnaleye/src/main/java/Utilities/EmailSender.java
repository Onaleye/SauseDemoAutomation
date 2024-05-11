package Utilities;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.DataHandler;

import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String recipient, String subject, String body, String reportFilePath) throws MessagingException {
        // Email configurations
        String senderEmail = "onaleyeolayinka@gmail";
        String senderPassword = "Ynet3214$$%%";
        String smtpHost = "smtp.gmail.com";
        int smtpPort = 587;

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create session
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // Create message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);

        // Create multipart message
        MimeMultipart multipart = new MimeMultipart();

        // Add text body
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(body);
        multipart.addBodyPart(bodyPart);

        // Add attachment (Extent Report)
        MimeBodyPart attachmentPart = new MimeBodyPart();
        DataSource source = new FileDataSource(reportFilePath);
        attachmentPart.setDataHandler(new DataHandler(source));
        attachmentPart.setFileName(reportFilePath);
        multipart.addBodyPart(attachmentPart);

        // Set content
        message.setContent(multipart);

        // Send message
        Transport.send(message);
    }

}

