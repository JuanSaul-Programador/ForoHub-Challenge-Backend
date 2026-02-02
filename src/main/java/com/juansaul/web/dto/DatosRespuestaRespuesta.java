package com.juansaul.web.dto;

import java.time.LocalDateTime;

public record DatosRespuestaRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        Long idTopico,
        Long idAutor,
        Boolean solucion
) {}
