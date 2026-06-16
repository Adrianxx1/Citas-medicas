package com.adrian.citas.mappers;

import org.springframework.stereotype.Component;

import com.adrian.citas.dto.CitaRequest;
import com.adrian.citas.dto.CitaResponse;
import com.adrian.citas.entities.Cita;
import com.adrian.commons.dto.DatosMedico;
import com.adrian.commons.dto.DatosPaciente;
import com.adrian.commons.dto.MedicoResponse;
import com.adrian.commons.mappers.CommonMapper;

@Component
public class CitaMapper implements CommonMapper<CitaRequest, CitaResponse, Cita> {

    @Override
    public Cita requestAEntidad(CitaRequest request) {
        if (request == null) return null;
        return Cita.crear(
                request.idPaciente(),
                request.idMedico(),
                request.fechaCita(),
                request.sintomas());
    }

    @Override
    public CitaResponse entidadAResponse(Cita entidad) {
        return entidadAResponse(entidad, null, null);
    }

    public CitaResponse entidadAResponse(Cita entidad, DatosPaciente paciente, MedicoResponse medico) {
        if (entidad == null) return null;
        return new CitaResponse(
                entidad.getId(),
                paciente,
                medicoResponseADatosMedico(medico),
                entidad.getFechaCita(),
                entidad.getSintomas(),
                entidad.getEstadoCita().getDescripcion());
    }

    private DatosMedico medicoResponseADatosMedico(MedicoResponse medico) {
        if (medico == null) return null;
        return new DatosMedico(
                medico.nombre(),
                medico.cedulaProfesional(),
                medico.especialidad());
    }
}