package com.adrian.citas.repositories;

import com.adrian.citas.entities.Cita;
import com.adrian.citas.enums.EstadoCita;
import com.adrian.commons.enums.EstadoRegistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByEstadoRegistro(EstadoRegistro estadoRegistro);
    Optional<Cita> findByIdAndEstadoRegistro(Long id, EstadoRegistro estadoRegistro);
    boolean existsByIdPacienteAndEstadoRegistroAndEstadoCitaIn(Long idPaciente, EstadoRegistro estadoRegistro,
                                                               List<EstadoCita> estadosCita);
    boolean existsByIdMedicoAndEstadoRegistroAndEstadoCitaIn(Long idMedico, EstadoRegistro estadoRegistro,
                                                             List<EstadoCita> estadosCita);
}