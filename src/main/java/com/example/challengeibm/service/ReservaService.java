package com.example.challengeibm.service;

import com.example.challengeibm.domain.Reserva;
import com.example.challengeibm.dto.ReservaDto;
import com.example.challengeibm.enums.Status;
import com.example.challengeibm.repository.ReservaRepository;
import com.example.challengeibm.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Reserva findById (Integer id) {
        Optional<Reserva> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Reservation not found, confirm the id."));
    }


    private ReservaDto convertToDto (Reserva objDto) {
        return new ReservaDto(
                objDto.getId(),
                objDto.getNomeHospede(),
                objDto.getDataInicio(),
                objDto.getDataFim(),
                objDto.getQuantidadePessoas(),
                objDto.getStatus()
        );
    }

    private Reserva convertToDomain (ReservaDto obj) {
        return new Reserva(
                obj.getId(),
                obj.getNomeHospede(),
                obj.getDataInicio(),
                obj.getDataFim(),
                obj.getQuantidadePessoas(),
                obj.getStatus()
        );
    }



}
