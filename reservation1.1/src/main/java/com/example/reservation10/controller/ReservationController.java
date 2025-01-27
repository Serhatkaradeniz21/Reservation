package com.example.reservation10.controller;
import org.springframework.ui.Model;


import com.example.reservation10.model.Reservation;
import com.example.reservation10.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "/reservation-form";
    }

    @PostMapping
    public String submitReservation(@ModelAttribute Reservation reservation) {
        reservationService.saveReservation(reservation);
        return "confirmation";
    }
}

