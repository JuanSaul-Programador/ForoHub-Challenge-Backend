package com.juansaul.web.dto;

import jakarta.validation.constraints.Size;

public record DatosActualizarUsuario(
        String nombre,
        
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String contrasena
) {}
