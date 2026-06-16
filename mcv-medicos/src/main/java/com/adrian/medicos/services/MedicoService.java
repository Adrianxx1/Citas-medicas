package com.adrian.medicos.services;

import com.adrian.commons.dto.MedicoRequest;
import com.adrian.commons.dto.MedicoResponse;
import com.adrian.commons.services.CrudService;

public interface MedicoService extends CrudService<MedicoRequest, MedicoResponse> {
	
	MedicoResponse obtenerMedicoPorIdSinEstado(Long id);
	
	void actualizarDisponibilidadMedico(Long idMedico, Long idDisponibilidad);

}
