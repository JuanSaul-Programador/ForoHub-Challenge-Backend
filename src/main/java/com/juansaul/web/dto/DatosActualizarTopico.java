package com.juansaul.web.dto;

import com.juansaul.domain.model.StatusTopico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotBlank(message = "El título es obligatorio")
        String titulo,
        
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,
        
        @NotNull(message = "El estado es obligatorio")
        StatusTopico status,
        
        @NotNull(message = "El ID del autor es obligatorio")
        Long idAutor,
        
        @NotNull(message = "El ID del curso es obligatorio")
        Long idCurso
) {}
