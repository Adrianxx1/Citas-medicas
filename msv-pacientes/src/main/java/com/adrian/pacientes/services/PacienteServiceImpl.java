package com.adrian.pacientes.services;

import com.adrian.commons.dto.PacienteRequest;
import com.adrian.commons.dto.PacienteResponse;
import com.adrian.commons.enums.EstadoRegistro;
import com.adrian.commons.exceptions.EntidadRelacionadaException;
import com.adrian.commons.exceptions.RecursoNoEncontradoException;
import com.adrian.pacientes.entities.Paciente;
import com.adrian.pacientes.mappers.PacienteMapper;
import com.adrian.pacientes.repositories.PacienteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PacienteResponse> listar() {
        log.info("Listado de todos los pacientes activos");
        return pacienteRepository.findByEstadoRegistro(EstadoRegistro.ACTIVO).stream()
                .map(pacienteMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteResponse obtenerPorId(Long id) {
        log.info("Buscando paciente activo con id: {}", id);
        return pacienteMapper.entidadAResponse(obtenerPacienteActivoOException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteResponse obtenerPacienteSinEstado(Long id) {
        log.info("Buscando paciente sin validar estado con id: {}", id);
        return pacienteMapper.entidadAResponse(
                pacienteRepository.findById(id)
                        .orElseThrow(() -> new RecursoNoEncontradoException("Paciente no encontrado con id: " + id)));
    }

    @Override
    public PacienteResponse registrar(PacienteRequest request) {
        log.info("Registrando nuevo paciente: {}", request);
        validarEmailUnico(request.email(), null);
        validarTelefonoUnico(request.telefono(), null);
        Paciente paciente = Paciente.crear(
                request.nombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno(),
                request.edad(),
                request.peso(),
                request.estatura(),
                request.email(),
                request.telefono(),
                request.direccion()
        );
        pacienteRepository.save(paciente);
        log.info("Paciente registrado exitosamente");
        return pacienteMapper.entidadAResponse(paciente);
    }

    @Override
    public PacienteResponse actualizar(PacienteRequest request, Long id) {
        log.info("Actualizando paciente con id: {}", id);
        Paciente paciente = obtenerPacienteActivoOException(id);
        validarEmailUnico(request.email(), id);
        validarTelefonoUnico(request.telefono(), id);
        paciente.actualizar(
                request.nombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno(),
                request.edad(),
                request.peso(),
                request.estatura(),
                request.email(),
                request.telefono(),
                request.direccion()
        );
        log.info("Paciente actualizado exitosamente con id: {}", id);
        return pacienteMapper.entidadAResponse(paciente);
    }

    @Override
    public void eliminar(Long id) {
        log.info("Eliminando paciente con id: {}", id);
        Paciente paciente = obtenerPacienteActivoOException(id);
        paciente.eliminar();
        log.info("Paciente con id {} marcado como eliminado", id);
    }

    private Paciente obtenerPacienteActivoOException(Long id) {
        log.info("Buscando paciente activo con id: {}", id);
        return pacienteRepository.findByIdAndEstadoRegistro(id, EstadoRegistro.ACTIVO)
                .orElseThrow(() -> new RecursoNoEncontradoException("Paciente activo no encontrado con id: " + id));
    }

    private void validarEmailUnico(String email, Long idExcluir) {
        boolean existe = idExcluir == null
                ? pacienteRepository.existsByEmailIgnoreCaseAndEstadoRegistro(email, EstadoRegistro.ACTIVO)
                : pacienteRepository.existsByEmailIgnoreCaseAndEstadoRegistroAndIdNot(email, EstadoRegistro.ACTIVO, idExcluir);
        if (existe)
            throw new EntidadRelacionadaException("Ya existe un paciente activo con el email: " + email);
    }

    private void validarTelefonoUnico(String telefono, Long idExcluir) {
        boolean existe = idExcluir == null
                ? pacienteRepository.existsByTelefonoAndEstadoRegistro(telefono, EstadoRegistro.ACTIVO)
                : pacienteRepository.existsByTelefonoAndEstadoRegistroAndIdNot(telefono, EstadoRegistro.ACTIVO, idExcluir);
        if (existe)
            throw new EntidadRelacionadaException("Ya existe un paciente activo con el teléfono: " + telefono);
    }
}