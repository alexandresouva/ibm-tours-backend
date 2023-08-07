package com.example.challengeibm.service;

import com.example.challengeibm.domain.Reserva;
import com.example.challengeibm.dto.ReservaDto;
import com.example.challengeibm.enums.Status;
import com.example.challengeibm.repository.ReservaRepository;
import com.example.challengeibm.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    public ReservaDto insert (ReservaDto reservaDto) {
        reservaDto.setStatus(Status.CONFIRMADA);

        // Converto a DTO em Domain para salva no banco
        Reserva reserva = convertToDomain(reservaDto);
        repository.save(reserva);

        // Recebo o Domain com o ID incluso, converto em DTO e retorno ao Controller
        reservaDto = convertToDto(reserva);
        return reservaDto;
    }


    public ReservaDto findById (Integer id) {
        Optional<Reserva> reserva = repository.findById(id);

        if (reserva.isEmpty()) {
            throw new ObjectNotFoundException("Reservation not found, confirm the id.");
        }

        ReservaDto reservaDto = convertToDto(reserva.get());
        return reservaDto;
    }

    public List<ReservaDto> findAll() {
        List<Reserva> reservas = repository.findAll();
        List<ReservaDto> dtos = new ArrayList<>();

        for (Reserva reserva : reservas) {
            ReservaDto dto = convertToDto(reserva);
            dtos.add(dto);
        }

        return dtos;
    }

    public ReservaDto updateReservationData(ReservaDto reservaDto, Integer id) {
        Reserva reserva = convertToDomain(findById(id));

        reserva.setNomeHospede(reservaDto.getNomeHospede());
        reserva.setDataInicio(reservaDto.getDataInicio());
        reserva.setDataFim(reservaDto.getDataFim());
        reserva.setQuantidadePessoas(reservaDto.getQuantidadePessoas());
        reserva.setStatus(reservaDto.getStatus());
        repository.save(reserva);

        ReservaDto reservaAtualizada = convertToDto(reserva);
        reservaAtualizada.setId(reserva.getId());

        return reservaAtualizada;
    }

    public ReservaDto cancel(Integer id) {
        Reserva reserva = convertToDomain(findById(id));

        reserva.setStatus(Status.CANCELADA);
        repository.save(reserva);

        ReservaDto reservaAtualizada = convertToDto(reserva);
        reservaAtualizada.setId(reserva.getId());

        return reservaAtualizada;
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

    private Reserva convertToDomain (ReservaDto objDto) {
        return new Reserva(
                objDto.getId(),
                objDto.getNomeHospede(),
                objDto.getDataInicio(),
                objDto.getDataFim(),
                objDto.getQuantidadePessoas(),
                objDto.getStatus()
        );
    }
}
