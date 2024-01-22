package roomescape.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roomescape.Repository.ReservationRepository;
import roomescape.domain.Reservation;


import java.net.URI;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok(reservationRepository.findAll());
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> addReservation(@Valid @RequestBody Reservation newReservation) {
        Reservation reservation = reservationRepository.save(newReservation);
        return ResponseEntity
                .created(URI.create("/reservations/" + reservation.getId()))
                .body(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {
        reservationRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
