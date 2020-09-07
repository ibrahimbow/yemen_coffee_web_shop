package intecbrussel.yemencoffee_webshop.repositories.implementation;

import intecbrussel.yemencoffee_webshop.model.ReceiveEmailFromUser;
import intecbrussel.yemencoffee_webshop.repositories.ReceiveEmailFromUserRepo;
import org.springframework.stereotype.Repository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Repository
public class ReceiveEmailFromUserRepoImpl implements ReceiveEmailFromUserRepo {

    @Override
    public void receiveEmail(ReceiveEmailFromUser receiveEmailFromUser) {

        String username= receiveEmailFromUser.getSendEmailInfo().getUsername();
        String password= receiveEmailFromUser.getSendEmailInfo().getPassword();
        String emailSend = receiveEmailFromUser.getSendEmailInfo().getFromEmail();
        Properties properties = new Properties();

            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.live.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username,password);
                }
            });

            //Start our mail message
            MimeMessage msg = new MimeMessage(session);
            try {
                msg.setFrom(new InternetAddress(emailSend));
                // here we add the receiver email for all user questions
                msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(receiveEmailFromUser.getEmailToReceiveUserQuestion()));
                // here we use the subject of the contact form
                msg.setSubject(receiveEmailFromUser.getSubjectWhoHasQuestion());

                Multipart emailContent = new MimeMultipart();

                //Text body part
                MimeBodyPart textBodyPart = new MimeBodyPart();

                textBodyPart.setText("Message from: " + receiveEmailFromUser.getUsernameWhoHasQuestion()+
                        "\n\n"+ " Email: " + receiveEmailFromUser.getEmailToReceiveUserQuestion()+
                        "\n\n" + "Subject: "+receiveEmailFromUser.getSubjectWhoHasQuestion()+
                        "\n\nMessage: \n\n" + receiveEmailFromUser.getMessageWhoHasQuestion());

                emailContent.addBodyPart(textBodyPart);
                msg.setContent(emailContent);

                Transport.send(msg);
                System.out.println("Sent message");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
}

