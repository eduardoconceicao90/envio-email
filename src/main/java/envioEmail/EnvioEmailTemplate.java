package envioEmail;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EnvioEmailTemplate {

    private String username = "eduardodevjavaweb@gmail.com";
    private String senha = "nxev ubuc bxfa ikmv";

    private String listaDestinatarios, nomeRemetente, assuntoEmail, templatePath;
    private Context thymeleafContext;

    public EnvioEmailTemplate(String listaDestinatarios, String nomeRemetente, String assuntoEmail, String templatePath) {
        this.listaDestinatarios = listaDestinatarios;
        this.nomeRemetente = nomeRemetente;
        this.assuntoEmail = assuntoEmail;
        this.templatePath = templatePath;
        this.thymeleafContext = new Context();
    }

    public void adicionarVariavelThymeleaf(String chave, Object valor) {
        thymeleafContext.setVariable(chave, valor);
    }

    public void enviarEmail() throws MessagingException, UnsupportedEncodingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, senha);
            }

        });

        session.setDebug(true);

        try (Transport transport = session.getTransport("smtp")) {

            Address[] toUser = InternetAddress.parse(listaDestinatarios);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, nomeRemetente, "UTF-8"));
            message.setRecipients(RecipientType.TO, toUser);
            message.setSubject(assuntoEmail);

            String emailBody = processarTemplateThymeleaf();

            message.setContent(emailBody, "text/html; charset=utf-8");

            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
        }
    }

    private String processarTemplateThymeleaf() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process(templatePath, thymeleafContext);
    }

}