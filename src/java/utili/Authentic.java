package utili;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author Felipe
 */
public class Authentic extends Authenticator {

    private final String email;
    private final String password;

    public Authentic(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(email, password);
    }

}
