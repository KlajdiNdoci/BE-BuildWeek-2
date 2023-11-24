package EPIC_ENERGY_SERVICE.BEBuildWeek2.config;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Utente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.NewEmailDTO;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailSender {
    private final String apikey;
    private final String sender;

    public EmailSender(@Value("${sendgrid.apikey}") String apikey,
                       @Value("${sengrid.sender}") String sender) {
        this.apikey = apikey;
        this.sender = sender;
    }

    public void sendRegistrationEmail(String recipient) throws IOException {
        Email from = new Email(sender);
        String subject = "Registrazione avvenuta con successo!";
        Email to = new Email(recipient);
        Content content = new Content("text/html",
                "<p>Benvenuto su EPIC ENERGY SERVICES</p>" +
                        "<p><a href=\"http://localhost:3000\"><button style=\"padding:10px; background-color:blue; color:white; text-decoration:none; border:none; border-radius:5px; cursor:pointer;\">Clicca qui</button></a></p>"
        );
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apikey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        sg.api(request);
    }

    public void sendEmail(NewEmailDTO body, Utente currentUser) throws IOException {
        Email from = new Email(currentUser.getEmail());
        String subject = body.oggetto();
        Email to = new Email(body.destinatario());
        Content content = new Content("text/html", body.contenuto());
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(apikey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        sg.api(request);
    }
}

