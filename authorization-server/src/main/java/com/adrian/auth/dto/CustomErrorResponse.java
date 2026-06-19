package com.adrian.commons.dto;

public record CustomErrorResponse(
        int codigo,
        String mensaje
) {
}

