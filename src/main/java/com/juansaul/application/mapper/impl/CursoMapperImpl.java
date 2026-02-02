package com.juansaul.application.mapper.impl;

import com.juansaul.application.mapper.CursoMapper;
import com.juansaul.domain.model.Curso;
import com.juansaul.web.dto.DatosRegistroCurso;
import com.juansaul.web.dto.DatosRespuestaCurso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapperImpl implements CursoMapper {

    @Override
    public Curso toEntity(DatosRegistroCurso dto) {
        if (dto == null) return null;
        
        return Curso.builder()
                .nombre(dto.nombre())
                .categoria(dto.categoria())
                .build();
    }

    @Override
    public DatosRespuestaCurso toDto(Curso curso) {
        if (curso == null) return null;
        
        return new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
    }
}
