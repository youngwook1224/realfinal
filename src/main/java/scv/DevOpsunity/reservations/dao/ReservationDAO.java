package scv.DevOpsunity.reservations.dao;

import org.apache.ibatis.annotations.Mapper;
import scv.DevOpsunity.reservations.dto.ReservationDTO;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ReservationDAO {
    void createReservation(ReservationDTO reservation);
    ReservationDTO getReservationById(Long id);
    List<ReservationDTO> getReservationsBySeatAndTimeRange(int seatNumber, LocalDateTime startTime, LocalDateTime endTime);
    List<ReservationDTO> getReservationsForDateRange(LocalDateTime startTime, LocalDateTime endTime);
    void deleteReservation(Long id);
    List<Integer> getOccupiedSeatsAtTime(LocalDateTime dateTime);

}