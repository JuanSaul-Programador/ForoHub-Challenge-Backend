package com.juansaul.application.mapper.impl;

import com.juansaul.application.mapper.RespuestaMapper;
import com.juansaul.domain.model.Respuesta;
import com.juansaul.domain.model.Topico;
import com.juansaul.domain.model.Usuario;
import com.juansaul.web.dto.DatosRegistroRespuesta;
import com.juansaul.web.dto.DatosRespuestaRespuesta;
import org.springframework.stereotype.Component;

@Component
public class RespuestaMapperImpl implements RespuestaMapper {

    @Override
    public Respuesta toEntity(DatosRegistroRespuesta dto, Topico topico, Usuario autor) {
        if (dto == null) return null;
        
        return Respuesta.builder()
                .mensaje(dto.mensaje())
                .topico(topico)
                .autor(autor)
                .solucion(false)
                .build();
    }

    @Override
    public DatosRespuestaRespuesta toDto(Respuesta respuesta) {
        if (respuesta == null) return null;
        
        return new DatosRespuestaRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getTopico().getId(),
                respuesta.getAutor().getId(),
                respuesta.getSolucion()
        );
    }
}
