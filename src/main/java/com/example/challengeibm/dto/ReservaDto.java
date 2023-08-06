package com.example.challengeibm.dto;

import com.example.challengeibm.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaDto {


//    CONFIRMAR
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private String nomeHospede;


    private LocalDate dataInicio;


    private LocalDate dataFim;
    private Integer quantidadePessoas;


    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Status status;

    // Constructos

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

    // Getters e Setters

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
