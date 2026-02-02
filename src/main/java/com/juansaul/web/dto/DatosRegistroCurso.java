package com.juansaul.web.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(
        @NotBlank(message = "El nombre del curso es obligatorio")
        String nombre,
        
        @NotBlank(message = "La categoría es obligatoria")
        String categoria
) {}
