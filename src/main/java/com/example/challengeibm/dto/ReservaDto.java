package com.example.challengeibm.dto;

import com.example.challengeibm.domain.Reserva;
import com.example.challengeibm.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

import java.time.LocalDate;


public class ReservaDto {
    private Integer id;

    @NotBlank
    private String nomeHospede;

    @NotNull
    @FutureOrPresent
    private LocalDate dataInicio;

    @NotNull
    @FutureOrPresent
    private LocalDate dataFim;

    @NotNull
    @Min(value = 1)
    private Integer quantidadePessoas;

    @Enumerated(EnumType.STRING)
    private Status status;

    //    Constructors

    public ReservaDto() {
    }

    public ReservaDto(Integer id, String nomeHospede, LocalDate dataInicio, LocalDate dataFim, Integer quantidadePessoas, Status status) {
        this.id = id;
        this.nomeHospede = nomeHospede;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.quantidadePessoas = quantidadePessoas;
        this.status = status;
    }

    public ReservaDto(Reserva obj) {
        id = obj.getId();
        nomeHospede = obj.getNomeHospede();
        dataInicio = obj.getDataInicio();
        dataFim = obj.getDataFim();
        quantidadePessoas = obj.getQuantidadePessoas();
        status = obj.getStatus();
    }

    //    Getters | Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(Integer quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
