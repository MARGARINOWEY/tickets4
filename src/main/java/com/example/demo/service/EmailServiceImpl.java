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
    @Autowired
    private ITicketService ticketService;

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
    public void enviarMensajeRegistro(String toUser, String subject, Integer monto_pagar, String evento, String link, String mesa, String dias) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");

        final Context ctx = new Context();

        ctx.setVariable("monto_pagar", monto_pagar);
        ctx.setVariable("evento", evento);
        ctx.setVariable("link", link);
        ctx.setVariable("mesa", mesa);
        ctx.setVariable("dias", dias);

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
    
    @Override
    public void enviarMensajeRegistro3(Integer num_asientos, String nombre,String toUser, String subject, Integer monto_pagar, String evento,String link, String mesa) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        final Context ctx = new Context();
        ctx.setVariable("nombre", nombre);
        ctx.setVariable("monto_pagar", monto_pagar);
        ctx.setVariable("evento", evento);
        ctx.setVariable("link", link);
        ctx.setVariable("mesa", mesa);
        ctx.setVariable("asientos", num_asientos);

        final String htmlContent = templateEngine.process("Ticket/compraEmail3.html", ctx);
        

        try {
            helper.setTo(toUser);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void enviarMensajeRegistro2(String toUser, String subject, Integer monto_pagar, String evento, String link, String mesa) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        final Context ctx = new Context();

        ctx.setVariable("monto_pagar", monto_pagar);
        ctx.setVariable("evento", evento);
        ctx.setVariable("link", link);
        ctx.setVariable("mesa", mesa);

        final String htmlContent = templateEngine.process("Ticket/compraEmail2.html", ctx);
        

        try {
            helper.setTo(toUser);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void enviarMensajeV50(String toUser, String subject, String evento, String link, String sector) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        final Context ctx = new Context();
        ctx.setVariable("link", link);
        ctx.setVariable("evento", evento);
        ctx.setVariable("sector", sector);
        final String htmlContent = templateEngine.process("Ticket/CorreoValidacion50.html", ctx);
        try {
            helper.setTo(toUser);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void enviarMensajeV100(String toUser, String subject, String evento, String link, String sector) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        final Context ctx = new Context();
        ctx.setVariable("link", link);
        ctx.setVariable("evento", evento);
        ctx.setVariable("sector", sector);
        final String htmlContent = templateEngine.process("Ticket/EnvioTickets.html", ctx);
        try {
            helper.setTo(toUser);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void enviarMensajeRechazo2(String toUser, String subject, String evento, String link, String sector,String motivo) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        final Context ctx = new Context();
        ctx.setVariable("link", link);
        ctx.setVariable("evento", evento);
        ctx.setVariable("sector", sector);
        ctx.setVariable("motivo", motivo);
        final String htmlContent = templateEngine.process("Ticket/CorreoRechazo2.html", ctx);
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
