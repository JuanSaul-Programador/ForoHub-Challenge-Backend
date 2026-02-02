package com.juansaul.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DatosRegistroTopico {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;

    @NotNull(message = "El ID del autor es obligatorio")
    private Long idAutor;

    @NotNull(message = "El ID del curso es obligatorio")
    private Long idCurso;
}