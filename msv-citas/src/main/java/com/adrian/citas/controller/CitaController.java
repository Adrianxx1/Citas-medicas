package com.adrian.citas.controller;

import com.adrian.citas.dto.CitaRequest;
import com.adrian.citas.dto.CitaResponse;
import com.adrian.citas.service.CitaService;
import com.adrian.commons.controllers.CommonController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/citas")
public class CitaController extends CommonController<CitaRequest, CitaResponse, CitaService> {
    public CitaController(CitaService service) {
        super(service);
    }
}