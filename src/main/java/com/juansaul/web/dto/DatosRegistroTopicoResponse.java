package com.juansaul.web.dto;

import com.juansaul.domain.model.StatusTopico;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DatosRegistroTopicoResponse {
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private StatusTopico status;
    private String autor;
    private String curso;
}
