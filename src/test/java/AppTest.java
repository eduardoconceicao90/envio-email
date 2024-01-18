import envioEmail.EnvioEmail;
import envioEmail.EnvioEmailAnexo;
import envioEmail.EnvioEmailTemplate;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public class AppTest {

    private String msg = "Mensagem html no envio de e-mail";
    private String msgAnexo = "Mensagem html no envio de e-mail anexo";

    @Test
    public void testeEmail() throws MessagingException, UnsupportedEncodingException {

        StringBuilder mensagemHtml = new StringBuilder();

        mensagemHtml.append("<b>Testando HTML</b><br/>");
        mensagemHtml.append("<b>Envio e-mail: </b>").append(msg).append("<br/><br/>");
        mensagemHtml.append("<a target=\"_blank\" href=\"https://google.com.br\"> CLIQUE AQUI");

        EnvioEmail envioEmail = new EnvioEmail(
                  "eduardosaconceicao@gmail.com, eduardodevjavaweb@gmail.com",
                  "Eduardo - Java Web",
                    "Envio e-mail",
                                mensagemHtml.toString()
        );

        envioEmail.enviarEmail();

    }

    @Test
    public void testeEmailAnexo() throws Exception {

        StringBuilder mensagemHtml = new StringBuilder();

        mensagemHtml.append("<b>Testando HTML</b><br/>");
        mensagemHtml.append("<b>Envio e-mail anexo: </b>").append(msgAnexo).append("<br/><br/>");
        mensagemHtml.append("<a target=\"_blank\" href=\"https://google.com.br\"> CLIQUE AQUI");

        EnvioEmailAnexo envioEmailAnexo = new EnvioEmailAnexo(
                "eduardosaconceicao@gmail.com, eduardodevjavaweb@gmail.com",
                "Eduardo - Java Web",
                "Envio e-mail anexo",
                mensagemHtml.toString()
        );

        envioEmailAnexo.enviarEmailAnexo();

    }

    @Test
    public void testeEmailTemplate() throws MessagingException, UnsupportedEncodingException {

        String nomeDestinario = "Destinatário";
        String link = "https://google.com.br";

        EnvioEmailTemplate envioEmailTemplate = new EnvioEmailTemplate(
                "eduardosaconceicao@gmail.com, eduardodevjavaweb@gmail.com",
                "Eduardo - Java Web",
                "Envio e-mail template",
                "email/testeEmail" // Substitua pelo caminho real do seu template HTML
        );

        envioEmailTemplate.adicionarVariavelThymeleaf("usuario", nomeDestinario);
        envioEmailTemplate.adicionarVariavelThymeleaf("link", link);

        envioEmailTemplate.enviarEmail();

    }
}
