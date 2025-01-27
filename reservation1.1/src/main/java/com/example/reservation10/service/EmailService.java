package com.example.reservation10.service;

import com.example.reservation10.model.Reservation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(Reservation reservation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reservation.getEmail());
        message.setSubject("Reservierungsbestätigung");
        message.setText("Vielen Dank für Ihre Reservierung, " + reservation.getName() + "!");
        mailSender.send(message);
    }
// TODO: not used
    public void notifyManager(Reservation reservation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("karadeniz.serhat21@gmail.com");
        message.setSubject("Neue Reservierung");
        message.setText("Es gibt eine neue Reservierung von: " + reservation.getName());
        mailSender.send(message);
    }

    private String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return dateTime.format(formatter);
    }
    public void sendNotificationToAdmin(Reservation reservation) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo("karadeniz.serhat21@gmail.com");
            helper.setSubject("Neue Reservierung erhalten");
            helper.setText(
                    "<h1>Neue Reservierung</h1>" +
                            "<p><strong>Name:</strong> " + reservation.getName() + "</p>" +
                            "<p><strong>E-Mail:</strong> " + reservation.getEmail() + "</p>" +
                            "<p><strong>Telefon:</strong> " + reservation.getPhone() + "</p>" +
                            "<p><strong>Datum und Uhrzeit:</strong> " +
                            formatDate(reservation.getReservationDate()) + "</p>" +
                            "<p><strong>Nachricht:</strong> " + reservation.getMessage() + "</p>",
                    true
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }



    }

}


