package co.edu.uniquindio.clinica.utils;


import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class EnvioEmail {
    public static void enviarNotificacion(String destinatario, String asunto, String mensaje) {
        // Enviar en un hilo separado para no bloquear la UI
        new Thread(() -> {
            Email email = EmailBuilder.startingBlank()
                    .from("melisayramona123@gmail.com")
                    .to(destinatario)
                    .withSubject(asunto)
                    .withPlainText(mensaje)
                    .buildEmail();

            try (Mailer mailer = MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 587, "melisayramona123@gmail.com",
                            "ibvabkcggmybfkwn")
                    .withTransportStrategy(TransportStrategy.SMTP_TLS)
                    .withDebugLogging(false)
                    .buildMailer()) {

                mailer.sendMail(email);
                System.out.println("Email enviado exitosamente a: " + destinatario);
            } catch (Exception e) {
                System.out.println("Nota: No se pudo enviar el email a " + destinatario + ". Error: " + e.getMessage());
                // No mostrar error en la UI, solo en console
            }
        }).start();
    }
}
