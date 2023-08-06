package com.example.challengeibm.controller;

import com.example.challengeibm.domain.Reserva;
import com.example.challengeibm.dto.ReservaDto;
import com.example.challengeibm.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @PostMapping
    public ResponseEntity<ReservaDto> insert (@RequestBody ReservaDto reserva) {

        ReservaDto obj = service.insert(reserva);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);

    }
}
