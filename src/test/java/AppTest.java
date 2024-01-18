import envioEmail.EnvioEmail;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


public class AppTest {

    private String msg = "Mensagem html no envio de e-mail";

    @Test
    public void testeEmail() throws MessagingException, UnsupportedEncodingException {

        StringBuilder mensagemHtml = new StringBuilder();

        mensagemHtml.append("<b>Testando HTML</b><br/>");
        mensagemHtml.append("<b>Teste: </b>").append(msg).append("<br/><br/>");
        mensagemHtml.append("<a target=\"_blank\" href=\"https://google.com.br\"> clique aqui");

        EnvioEmail envioEmail = new EnvioEmail(
                  "eduardosaconceicao@gmail.com, eduardodevjavaweb@gmail.com",
                  "Eduardo - Java Web",
                    "Teste",
                                mensagemHtml.toString()
        );

        envioEmail.enviarEmail();

    }
}
