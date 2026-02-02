package com.juansaul.application.service;

import com.juansaul.web.dto.DatosActualizarCurso;
import com.juansaul.web.dto.DatosRegistroCurso;
import com.juansaul.web.dto.DatosRespuestaCurso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CursoService {

    DatosRespuestaCurso registrar(DatosRegistroCurso datos);

    Page<DatosRespuestaCurso> listar(Pageable paginacion);

    DatosRespuestaCurso detallar(Long id);

    DatosRespuestaCurso actualizar(Long id, DatosActualizarCurso datos);

    void eliminar(Long id);
}
