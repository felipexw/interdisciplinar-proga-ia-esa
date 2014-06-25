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

    private final String email = "joguinhodavelhatictactoe@gmail.com";
    private final String password = "fudenciax";

    public void sendEmail(String destinationEmail, String destinationPassword) throws MessagingException {
        Properties connectionProperties = new Properties();
        connectionProperties.put("mail.smtp.host", "smtp.gmail.com");
        connectionProperties.put("mail.smtp.auth", "true");
        connectionProperties.put("mail.smtp.starttls.enable", "true");
        connectionProperties.put("mail.smtp.socketFactory.port", "465");
        connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        connectionProperties.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(connectionProperties,
                new AuthenticEmail(this.email, this.password));
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(this.email));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinationEmail));
        message.setSubject("Solicitação de Nova Senha");
        message.setText("Sua nova senha é: " + destinationPassword);
        Transport.send(message);
    }

}
