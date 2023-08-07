package com.example.challengeibm.service;

import com.example.challengeibm.domain.Reserva;
import com.example.challengeibm.dto.ReservaDto;
import com.example.challengeibm.enums.Status;
import com.example.challengeibm.repository.ReservaRepository;
import com.example.challengeibm.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    public ReservaDto createReservation (ReservaDto reservation) {
        validateReservationDates(reservation.getDataInicio(), reservation.getDataFim());

        reservation.setStatus(Status.CONFIRMADA);
        Reserva obj = convertToDomain(reservation);
        repository.save(obj);

        reservation = convertToDto(obj);
        return reservation;
    }

    public ReservaDto findReservationById (Integer id) {
        Optional<Reserva> reservation = repository.findById(id);

        if (reservation.isEmpty()) {
            throw new ObjectNotFoundException("Reserva não encontrada, confirme o id.");
        }

        ReservaDto reservationFound = convertToDto(reservation.get());
        return reservationFound;
    }

    public List<ReservaDto> findAllReservations() {
        List<Reserva> reservations = repository.findAll();
        List<ReservaDto> dtos = new ArrayList<>();

        for (Reserva reservation : reservations) {
            ReservaDto dto = convertToDto(reservation);
            dtos.add(dto);
        }

        return dtos;
    }

    public ReservaDto updateReservationData(ReservaDto reservation, Integer id) {
        validateReservationDates(reservation.getDataInicio(), reservation.getDataFim());
        Reserva obj = convertToDomain(findReservationById(id));

        obj.setNomeHospede(reservation.getNomeHospede());
        obj.setDataInicio(reservation.getDataInicio());
        obj.setDataFim(reservation.getDataFim());
        obj.setQuantidadePessoas(reservation.getQuantidadePessoas());
        obj.setStatus(reservation.getStatus());
        repository.save(obj);

        ReservaDto newReservation = convertToDto(obj);
        newReservation.setId(reservation.getId());

        return newReservation;
    }

    public ReservaDto cancelReservation(Integer id) {
        Reserva reservation = convertToDomain(findReservationById(id));

        reservation.setStatus(Status.CANCELADA);
        repository.save(reservation);

        ReservaDto reservationCanceled = convertToDto(reservation);
        reservationCanceled.setId(reservation.getId());

        return reservationCanceled;
    }

    private ReservaDto convertToDto (Reserva obj) {
        return new ReservaDto(
                obj.getId(),
                obj.getNomeHospede(),
                obj.getDataInicio(),
                obj.getDataFim(),
                obj.getQuantidadePessoas(),
                obj.getStatus()
        );
    }

    private Reserva convertToDomain (ReservaDto dto) {
        return new Reserva(
                dto.getId(),
                dto.getNomeHospede(),
                dto.getDataInicio(),
                dto.getDataFim(),
                dto.getQuantidadePessoas(),
                dto.getStatus()
        );
    }

    public void validateReservationDates(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("A data final da reserva deve ser maior que a data de início.");
        }
    }
}
