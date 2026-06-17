package com.adrian.pacientes.services;
import com.adrian.commons.dto.PacienteRequest;
import com.adrian.commons.dto.PacienteResponse;
import com.adrian.commons.services.CrudService;

public interface PacienteService extends CrudService<PacienteRequest, PacienteResponse> {
    PacienteResponse obtenerPacienteSinEstado(Long id);
}