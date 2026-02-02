package com.juansaul.application.service;

import com.juansaul.web.dto.DatosActualizarRespuesta;
import com.juansaul.web.dto.DatosRegistroRespuesta;
import com.juansaul.web.dto.DatosRespuestaRespuesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RespuestaService {

    DatosRespuestaRespuesta registrar(DatosRegistroRespuesta datos);

    Page<DatosRespuestaRespuesta> listar(Pageable paginacion);

    DatosRespuestaRespuesta detallar(Long id);

    DatosRespuestaRespuesta actualizar(Long id, DatosActualizarRespuesta datos);

    void eliminar(Long id);
}
