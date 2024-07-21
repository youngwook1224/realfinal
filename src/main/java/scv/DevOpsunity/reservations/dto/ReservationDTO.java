package scv.DevOpsunity.reservations.dto;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private Long memberId;
    private int seatNumber;
    private LocalDateTime reservationTime;
    private LocalDateTime endTime;

    // Getters and setters

    public ReservationDTO() {
        this.id = id;
        this.memberId = memberId;
        this.seatNumber = seatNumber;
        this.reservationTime = reservationTime;
        this.endTime = endTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}