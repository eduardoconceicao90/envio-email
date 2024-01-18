package envioEmail;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EnvioEmail {

    private String username = "eduardodevjavaweb@gmail.com";
    private String senha = "nxev ubuc bxfa ikmv";

    private String listaDestinatarios, nomeRemetente, assuntoEmail, msgEmail;

    public EnvioEmail(String listaDestinatarios, String nomeRemetente, String assuntoEmail, String msgEmail) {
        this.listaDestinatarios = listaDestinatarios;
        this.nomeRemetente = nomeRemetente;
        this.assuntoEmail = assuntoEmail;
        this.msgEmail = msgEmail;
    }

    public void enviarEmail() throws MessagingException, UnsupportedEncodingException {

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

        Address[] toUser = InternetAddress.parse(listaDestinatarios);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username, nomeRemetente, "UTF-8"));
        message.setRecipients(Message.RecipientType.TO, toUser);
        message.setSubject(assuntoEmail);
        message.setContent(msgEmail, "text/html; charset=utf-8");

        Transport.send(message);
    }


    /*
     * Esse método simula o PDF ou qualquer arquivo que possa ser enviado por anexo no email.
     * Voce pode pegar o arquino no seu banco de dados base64, byte[], Stream de Arquivos.
     * Pode estar e um banco de dados, ou em uma pasta.
     * Retorna um PDF em Branco com o texto do Paragrafo de exemplo.
    */
    private FileInputStream simuladorDePDF() throws Exception{

        Document document = new Document();
        File file = new File("fileanexo.pdf");
        file.createNewFile();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        document.add(new Paragraph("Conteudo do PDF anexo com Java Mail, esse texto é do PDF"));
        document.close();

        return new FileInputStream(file);
    }
}
