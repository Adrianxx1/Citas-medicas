package com.adrian.medicos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrian.commons.controllers.CommonController;
import com.adrian.commons.dto.MedicoRequest;
import com.adrian.commons.dto.MedicoResponse;
import com.adrian.medicos.services.MedicoService;

import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/medicos")
@Validated
public class MedicoController extends CommonController<MedicoRequest, MedicoResponse, MedicoService> {

    public MedicoController(MedicoService service) {
        super(service);
    }

    @GetMapping("/{id-medico}/{id}")
    public ResponseEntity<MedicoResponse> obtenerMedicoPorIdSinEstado(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id) {
        return ResponseEntity.ok(service.obtenerMedicoPorIdSinEstado(id));
    }

    @PutMapping("/{idMedico}/disponibilidad/{idDisponibilidad}")
    public ResponseEntity<Void> actualizarDisponibilidadMedico(
            @PathVariable @Positive(message = "El idMedico debe ser positivo") Long idMedico,
            @PathVariable @Positive(message = "El idDisponibilidad debe ser positivo") Long idDisponibilidad) {
        service.actualizarDisponibilidadMedico(idMedico, idDisponibilidad);
        return ResponseEntity.noContent().build();
    }
}