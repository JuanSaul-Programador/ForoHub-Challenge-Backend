package com.juansaul.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,
        
        @NotNull(message = "El ID del tópico es obligatorio")
        Long idTopico,
        
        @NotNull(message = "El ID del autor es obligatorio")
        Long idAutor
) {}
