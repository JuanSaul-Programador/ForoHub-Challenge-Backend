package com.juansaul.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,
        
        @NotNull(message = "El campo solucion es obligatorio")
        Boolean solucion
) {}
