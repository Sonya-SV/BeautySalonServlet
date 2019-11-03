//package com.training.salon.controller.command;
//
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Properties;
//
//public class SendEmail implements ICommand {
//    @Override
//    public String execute(HttpServletRequest request) {
//        final String username = "kate.nilson123@gmail.com";
//        final String password = "kate123kate";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//        session.setDebug(true);
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse("sofi.svyrydenko@gmail.com"));
//            message.setSubject("Leave Comment");
//            message.setText("http://localhost:8888/api");
//            Transport.send(message);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//        request.setAttribute("emailSent", "Email was sent");
//        return "/app/email";
//
//    }
//}
