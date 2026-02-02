package com.juansaul.application.mapper;

import com.juansaul.domain.model.Curso;
import com.juansaul.web.dto.DatosRegistroCurso;
import com.juansaul.web.dto.DatosRespuestaCurso;

public interface CursoMapper {
    
    Curso toEntity(DatosRegistroCurso dto);
    
    DatosRespuestaCurso toDto(Curso curso);
}
