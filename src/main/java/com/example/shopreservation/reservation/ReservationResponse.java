package com.example.shopreservation.reservation;

import java.time.LocalDateTime;

public record ReservationResponse(
    Long id,
    String customerName,
    String shopName,
    LocalDateTime reservedAt,
    ReservationStatus status
) {
}
