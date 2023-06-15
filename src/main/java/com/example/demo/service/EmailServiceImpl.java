package com.example.demo.service;

import java.io.File;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demo.entity.Persona;
import com.example.demo.entity.Usuario;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendEmail(String toUser, String subject, String message) {
        // TODO Auto-generated method stub
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("wayrasoluciones.tnt@gmail.com");
        mailMessage.setTo(toUser);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

    @Override
    public void sendEmailWithFile(String toUser, String subject, String message, File file) {
        // TODO Auto-generated method stub
        
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
            
            mimeMessageHelper.setFrom(toUser);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);
            mimeMessageHelper.addAttachment(file.getName(), file);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void enviarMensajeRegistro(String toUser, String subject, Integer monto_pagar, String evento, String link, String mesa) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        final Context ctx = new Context();

        ctx.setVariable("monto_pagar", monto_pagar);
        ctx.setVariable("evento", evento);
        ctx.setVariable("link", link);
        ctx.setVariable("mesa", mesa);

        final String htmlContent = templateEngine.process("Ticket/compraEmail.html", ctx);
        

        try {
            helper.setTo(toUser);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
