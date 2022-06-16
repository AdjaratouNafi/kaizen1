/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author DV7
 */
public class SendEmail {

    //final String fromEmail = "info@accel-solutions.com"; //requires valid gmail id
    //final String password = "NextGen19%#"; // correct password for gmail id
    final String fromEmail = "kaizen.accel@gmail.com"; //requires valid gmail id
    final String password = "Accel010"; // correct password for gmail id

    public SendEmail() {

    }

    public void sendEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean prepareEmail(String toEmail, String nom, String prenom, String gpwd) {
        try {

            System.out.println("TLSEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);

//            String urlAppli = "http://localhost:8080/Kaisen/";
            String urlAppli = "http://ec2-3-16-11-57.us-east-2.compute.amazonaws.com:8080/Kaisen/";

            this.sendEmail(session, toEmail, "Identifiant Kaï-zen ERP", "Bonjour " + nom + " " + prenom + " \n\n Vous trouverez ci-dessous les identifiants pour accéder à votre compte Kaï-zen ERP. \n Utilisateur: " + toEmail + " \n Mot de passe: " + gpwd + " \n Ce mot de passe est valide pour 24h. Une fois expiré, veuillez vous rapprocher de votre administrateur pour réinitialiser le mot de passe. \n Veuillez cliquer sur le lien suivant pour activer votre compte: " + urlAppli + " \n\n Bonne réception \n\n L'équipe Kaï-zen ERP");

        } catch (Exception e) {
        }
        return true;
    }

}
