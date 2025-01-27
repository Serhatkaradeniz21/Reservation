package com.example.reservation10.service;

import com.example.reservation10.model.Reservation;
import com.example.reservation10.repository.ReservationRepository;
import org.springframework.stereotype.Service;
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final EmailService emailService;

    public ReservationService(ReservationRepository reservationRepository, EmailService emailService) {
        this.reservationRepository = reservationRepository;
        this.emailService = emailService;
    }

    public void saveReservation(Reservation reservation) {
        // Speichert die Reservierung in der Datenbank
        reservationRepository.save(reservation);

        // Sendet Bestätigungsmail an den Benutzer
        emailService.sendConfirmationEmail(reservation);

        // Benachrichtigt den Manager
        //emailService.notifyManager(reservation);
        emailService.sendNotificationToAdmin(reservation);
    }
}









