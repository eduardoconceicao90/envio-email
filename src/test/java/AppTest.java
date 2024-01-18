import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


public class AppTest {

    private String username = "eduardodevjavaweb@gmail.com";
    private String senha = "nxev ubuc bxfa ikmv";

    @Test
    public void testeEmail() throws MessagingException, UnsupportedEncodingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls", "false");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, senha);
            }

        });

        session.setDebug(true);

        Address[] toUser = InternetAddress.parse("eduardosaconceicao@gmail.com, eduardodevjavaweb@gmail.com");

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username, "Eduardo - Java Web", "UTF-8"));
        message.setRecipients(Message.RecipientType.TO, toUser);
        message.setSubject("Teste");
        message.setContent("Testando envio de e-mail.", "text/html; charset=utf-8");

        Transport.send(message);

    }
}
