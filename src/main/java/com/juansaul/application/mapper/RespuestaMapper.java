package com.juansaul.application.mapper;

import com.juansaul.domain.model.Respuesta;
import com.juansaul.domain.model.Topico;
import com.juansaul.domain.model.Usuario;
import com.juansaul.web.dto.DatosRegistroRespuesta;
import com.juansaul.web.dto.DatosRespuestaRespuesta;

public interface RespuestaMapper {
    
    Respuesta toEntity(DatosRegistroRespuesta dto, Topico topico, Usuario autor);
    
    DatosRespuestaRespuesta toDto(Respuesta respuesta);
}
