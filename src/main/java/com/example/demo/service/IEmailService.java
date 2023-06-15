package com.example.demo.service;

import java.io.File;

public interface IEmailService {
    void sendEmail(String toUser, String subject, String message);
    void sendEmailWithFile(String toUser, String subject, String message, File file);
    public void enviarMensajeRegistro(String toUser, String subject, Integer monto_pagar, String evento,String link, String mesa);
}
