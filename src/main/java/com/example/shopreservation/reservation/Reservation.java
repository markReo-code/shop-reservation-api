package com.example.shopreservation.reservation;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String customerName;

    @Column(nullable = false, length = 255)
    private String customerEmail;

    @Column(nullable = false, length = 100)
    private String shopName;

    @Column(nullable = false)
    private LocalDateTime reservedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ReservationStatus status;

    protected Reservation() {

    }

    public Reservation(
        String customerName,
        String customerEmail,
        String shopName,
        LocalDateTime reservedAt,
        ReservationStatus status
    ) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.shopName = shopName;
        this.reservedAt = reservedAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getShopName() {
        return shopName;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }
    
    public ReservationStatus getStatus() {
        return status;
    }
    
}

