package com.adrian.citas.dto;

import com.adrian.commons.dto.DatosMedico;
import com.adrian.commons.dto.DatosPaciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CitaResponse(
        Long id,
        DatosPaciente paciente,
        DatosMedico medico,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm") LocalDateTime fechaCita,
        String sintomas,
        String estadoCita
) {
}