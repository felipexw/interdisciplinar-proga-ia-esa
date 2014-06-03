package utili;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Felipe Appio
 */
public class JavaMailSender {

    //"joguinhodavelhatictactoe@gmail.com"
    private final String email;
    //fudenciax
    private final String password;

    public JavaMailSender(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private void sendEmail(String destinationEmail, String destinationPassword) throws MessagingException {
        // Create all the needed properties
        Properties connectionProperties = new Properties();
        // SMTP host
        connectionProperties.put("mail.smtp.host", "smtp.gmail.com");
        // Is authentication enabled
        connectionProperties.put("mail.smtp.auth", "true");
        // Is TLS enabled
        connectionProperties.put("mail.smtp.starttls.enable", "true");
        // SSL Port
        connectionProperties.put("mail.smtp.socketFactory.port", "465");
        // SSL Socket Factory class
        connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // SMTP port, the same as SSL port :)
        connectionProperties.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(connectionProperties,
                new Authentic(this.email, this.password));

        // Create the message 
        Message message = new MimeMessage(session);
        // Set sender
        message.setFrom(new InternetAddress(this.email));
        // Set the recipients
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinationEmail));
        // Set message subject
        message.setSubject("Solicitação de Nova Senha");
        // Set message text
        message.setText("Sua nova senha é: " + destinationPassword);
        // Send the message
        Transport.send(message);
        System.out.println("Done!!");
    }

}
