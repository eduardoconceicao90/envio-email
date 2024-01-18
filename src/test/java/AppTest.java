import envioEmail.EnvioEmail;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


public class AppTest {

    private String username = "eduardodevjavaweb@gmail.com";
    private String senha = "nxev ubuc bxfa ikmv";

    @Test
    public void testeEmail() throws MessagingException, UnsupportedEncodingException {

        EnvioEmail envioEmail = new EnvioEmail(
                  "eduardosaconceicao@gmail.com, eduardodevjavaweb@gmail.com",
                  "Eduardo - Java Web",
                    "Teste",
                      "Testando envio de e-mail."
        );

        envioEmail.enviarEmail();

    }
}
