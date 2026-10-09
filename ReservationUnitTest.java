package moviereservation;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class ReservationUnitTest {

    @Test
    void testSeatIsReservedAfterReservation() {

        Movie movie = new Movie(
                "The Fast and the Furious",
                "Action",
                106,
                "PG-13"
        );

        Showtime showtime = new Showtime(
                movie,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusMinutes(136),
                new BigDecimal("15.50")
        );

        Seat seat = new Seat("F", 12, true);

        new Reservation("customer@email.com", showtime, seat);

        assertTrue(seat.isReserved());
    }

    @Test
    void testDuplicateReservationIsPrevented() {

        Movie movie = new Movie(
                "The Fast and the Furious",
                "Action",
                106,
                "PG-13"
        );

        Showtime showtime = new Showtime(
                movie,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusMinutes(136),
                new BigDecimal("15.50")
        );

        Seat seat = new Seat("F", 12, true);

        new Reservation("customer@email.com", showtime, seat);

        assertThrows(
                IllegalStateException.class,
                () -> new Reservation("second@email.com", showtime, seat)
        );
    }
}