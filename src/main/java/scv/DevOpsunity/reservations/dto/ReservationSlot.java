package scv.DevOpsunity.reservations.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationSlot {
    private LocalDateTime time;
    private List<Integer> availableSeats;

    public ReservationSlot(LocalDateTime time, List<Integer> availableSeats) {
        this.time = time;
        this.availableSeats = availableSeats;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<Integer> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Integer> availableSeats) {
        this.availableSeats = availableSeats;
    }
}