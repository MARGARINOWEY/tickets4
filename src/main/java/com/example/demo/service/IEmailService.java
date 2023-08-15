package com.example.demo.service;

import java.io.File;

public interface IEmailService {
    void sendEmail(String toUser, String subject, String message);
    void sendEmailWithFile(String toUser, String subject, String message, File file);
    public void enviarMensajeRegistro(String toUser, String subject, Integer monto_pagar, String evento,String link, String mesa);

    public void enviarMensajeRegistro2(String toUser, String subject, Integer monto_pagar, String evento,String link, String mesa);

    public void enviarMensajeRegistro3(Integer num_asientos, String nombre,String toUser, String subject, Integer monto_pagar, String evento,String link, String mesa);

    public void enviarMensajeV50(String toUser, String subject, String evento,String link, String sector);

    public void enviarMensajeV100(String toUser, String subject, String evento,String link, String sector);

    public void enviarMensajeRechazo2(String toUser, String subject, String evento,String link, String sector, String motivo);
}
