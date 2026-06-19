package com.adrian.pacientes.controller;

import com.adrian.commons.controllers.CommonController;
import com.adrian.commons.dto.PacienteRequest;
import com.adrian.commons.dto.PacienteResponse;
import com.adrian.pacientes.services.PacienteService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
@Validated
public class    PacienteController extends CommonController<PacienteRequest, PacienteResponse, PacienteService> {

    public PacienteController(PacienteService service) {
        super(service);
    }

    @GetMapping("/id-paciente/{id}")
    public ResponseEntity<PacienteResponse> obtenerPacientePorIdSinEstado(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id) {
        return ResponseEntity.ok(service.obtenerPacienteSinEstado(id));
    }

}
