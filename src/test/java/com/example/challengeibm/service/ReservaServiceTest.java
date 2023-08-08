package com.example.challengeibm.service;

import com.example.challengeibm.domain.Reserva;
import com.example.challengeibm.dto.ReservaDto;
import com.example.challengeibm.enums.Status;
import com.example.challengeibm.repository.ReservaRepository;
import com.example.challengeibm.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReservaServiceTest {

    @Mock
    private ReservaRepository repository;

    @InjectMocks
    private ReservaService service;

    private Reserva reservationMock;
    private ReservaDto dtoMock;

    @BeforeEach
    public void setUp() {
        reservationMock = new Reserva(1, "João", LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 5), 2, Status.CONFIRMADA);
        dtoMock = new ReservaDto(1, "João", LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 5), 2, Status.CONFIRMADA);
    }

    @Test
    public void testCreateReservation() {
        ReservaDto dto = new ReservaDto();
        dto.setNomeHospede("Maria");
        dto.setDataInicio(LocalDate.of(2023, 8, 10));
        dto.setDataFim(LocalDate.of(2023, 8, 15));
        dto.setQuantidadePessoas(3);

        when(repository.save(any(Reserva.class))).thenReturn(reservationMock);

        ReservaDto newDto = service.createReservation(dto);

        assertNotNull(newDto);
        assertEquals(dto.getNomeHospede(), newDto.getNomeHospede());
        assertEquals(dto.getDataInicio(), newDto.getDataInicio());
        assertEquals(dto.getDataFim(), newDto.getDataFim());
        assertEquals(dto.getQuantidadePessoas(), newDto.getQuantidadePessoas());
        assertEquals(Status.CONFIRMADA, newDto.getStatus());

        verify(repository, times(1)).save(any(Reserva.class));
    }


    @Test
    public void testFindReservationByIdExistente() {
        Integer idFound = reservationMock.getId();
        when(repository.findById(idFound)).thenReturn(Optional.of(reservationMock));

        ReservaDto newDto = service.findReservationById(idFound);

        assertNotNull(newDto);
        assertEquals(dtoMock.getId(), newDto.getId());
        assertEquals(dtoMock.getNomeHospede(), newDto.getNomeHospede());
        assertEquals(dtoMock.getDataInicio(), newDto.getDataInicio());
        assertEquals(dtoMock.getDataFim(), newDto.getDataFim());
        assertEquals(dtoMock.getQuantidadePessoas(), newDto.getQuantidadePessoas());
        assertEquals(dtoMock.getStatus(), newDto.getStatus());

        verify(repository, times(1)).findById(idFound);
    }


    @Test
    public void testFindReservationByIdNaoExistente() {
        Integer idNotExisting = 99;
        when(repository.findById(idNotExisting)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.findReservationById(idNotExisting));

        verify(repository, times(1)).findById(idNotExisting);
    }


    @Test
    public void testFindAllReservations() {
        List<Reserva> reservations = new ArrayList<>();
        reservations.add(reservationMock);

        when(repository.findAll()).thenReturn(reservations);

        List<ReservaDto> list = service.findAllReservations();

        // Assert
        assertNotNull(list);
        assertEquals(1, list.size());

        ReservaDto dtoFound = list.get(0);
        assertEquals(dtoMock.getId(), dtoFound.getId());
        assertEquals(dtoMock.getNomeHospede(), dtoFound.getNomeHospede());
        assertEquals(dtoMock.getDataInicio(), dtoFound.getDataInicio());
        assertEquals(dtoMock.getDataFim(), dtoFound.getDataFim());
        assertEquals(dtoMock.getQuantidadePessoas(), dtoFound.getQuantidadePessoas());
        assertEquals(dtoMock.getStatus(), dtoFound.getStatus());

        verify(repository, times(1)).findAll();
    }

    @Test
    public void testUpdateReservationData() {
        // Arrange
        Integer idFound = reservationMock.getId();
        ReservaDto updatedReservationDto = new ReservaDto();
        updatedReservationDto.setNomeHospede("Ana");
        updatedReservationDto.setDataInicio(LocalDate.of(2023, 9, 1));
        updatedReservationDto.setDataFim(LocalDate.of(2023, 9, 7));
        updatedReservationDto.setQuantidadePessoas(4);
        updatedReservationDto.setStatus(Status.CONFIRMADA);

        when(repository.findById(idFound)).thenReturn(Optional.of(reservationMock));
        when(repository.save(any(Reserva.class))).thenReturn(reservationMock);

        ReservaDto updateResult = service.updateReservationData(updatedReservationDto, idFound);

        assertNotNull(updateResult);
        assertEquals(idFound, updateResult.getId());
        assertEquals(updatedReservationDto.getNomeHospede(), updateResult.getNomeHospede());
        assertEquals(updatedReservationDto.getDataInicio(), updateResult.getDataInicio());
        assertEquals(updatedReservationDto.getDataFim(), updateResult.getDataFim());
        assertEquals(updatedReservationDto.getQuantidadePessoas(), updateResult.getQuantidadePessoas());
        assertEquals(updatedReservationDto.getStatus(), updateResult.getStatus());

        verify(repository, times(1)).findById(idFound);
        verify(repository, times(1)).save(any(Reserva.class));
    }


    @Test
    public void testCancelReservation() {
        Integer ifFound = reservationMock.getId();
        when(repository.findById(ifFound)).thenReturn(Optional.of(reservationMock));

        ReservaDto resultadoCancelamento = service.cancelReservation(ifFound);

        assertNotNull(resultadoCancelamento);
        assertEquals(ifFound, resultadoCancelamento.getId());
        assertEquals(dtoMock.getNomeHospede(), resultadoCancelamento.getNomeHospede());
        assertEquals(dtoMock.getDataInicio(), resultadoCancelamento.getDataInicio());
        assertEquals(dtoMock.getDataFim(), resultadoCancelamento.getDataFim());
        assertEquals(dtoMock.getQuantidadePessoas(), resultadoCancelamento.getQuantidadePessoas());
        assertEquals(Status.CANCELADA, resultadoCancelamento.getStatus());

        verify(repository, times(1)).findById(ifFound);
        verify(repository, times(1)).save(any(Reserva.class));
    }

}
