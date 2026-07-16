package com.example.shopreservation.reservation;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationResponse> getReservations() {
       return reservationRepository.findAll()
       .stream()
       .map(reservation -> new ReservationResponse(
          reservation.getId(),
          reservation.getCustomerName(),
          reservation.getShopName(),
          reservation.getReservedAt(),
          reservation.getStatus()
       ))
       .toList();
    }
}
