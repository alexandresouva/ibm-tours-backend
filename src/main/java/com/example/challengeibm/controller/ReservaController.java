package com.example.challengeibm.controller;

import com.example.challengeibm.domain.Reserva;
import com.example.challengeibm.dto.ReservaDto;
import com.example.challengeibm.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @PostMapping
    public ResponseEntity<ReservaDto> createReservation(@RequestBody @Valid ReservaDto data) {
        ReservaDto reservation = service.createReservation(data);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reservation.getId()).toUri();
        return ResponseEntity.created(uri).body(reservation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> findReservationById(@PathVariable Integer id) {
        ReservaDto reservation = service.findReservationById(id);
        return ResponseEntity.ok().body(reservation);
    }

    @GetMapping
    public ResponseEntity<List<ReservaDto>> findAllReservations() {
        List<ReservaDto> reservations = service.findAllReservations();
        return ResponseEntity.ok().body(reservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDto> updateReservation(@RequestBody ReservaDto data, @PathVariable Integer id) {
        ReservaDto reservation = service.updateReservationData(data, id);
        return ResponseEntity.ok().body(reservation);
    }

    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<ReservaDto> cancelReservation(@PathVariable Integer id) {
        ReservaDto reservation = service.cancelReservation(id);
        return ResponseEntity.ok().body(reservation);
    }
}
