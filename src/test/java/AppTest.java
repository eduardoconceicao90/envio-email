import org.junit.Test;

import java.util.Properties;

public class AppTest {

    private String userName = "eduardodevjavaweb@gmail.com";
    private String senha = "dydqzvkgqaiytfsb";

    @Test
    public void testeEmail(){

        Properties properties = new Properties();
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls", "false");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }
}
