package com.juansaul.application.mapper.impl;

import com.juansaul.application.mapper.TopicoMapper;
import com.juansaul.domain.model.Curso;
import com.juansaul.domain.model.StatusTopico;
import com.juansaul.domain.model.Topico;
import com.juansaul.domain.model.Usuario;
import com.juansaul.web.dto.DatosRegistroTopico;
import com.juansaul.web.dto.DatosRegistroTopicoResponse;
import org.springframework.stereotype.Component;

@Component
public class TopicoMapperImpl implements TopicoMapper {

    @Override
    public Topico toEntity(DatosRegistroTopico dto, Usuario autor, Curso curso) {
        if (dto == null) return null;

        return Topico.builder()
                .titulo(dto.getTitulo())
                .mensaje(dto.getMensaje())
                .status(StatusTopico.NO_RESPONDIDO)
                .autor(autor)
                .curso(curso)
                .build();
    }

    @Override
    public DatosRegistroTopicoResponse toDto(Topico topico) {
        if (topico == null) return null;
        return DatosRegistroTopicoResponse.builder()
                .id(topico.getId())
                .titulo(topico.getTitulo())
                .mensaje(topico.getMensaje())
                .fechaCreacion(topico.getFechaCreacion())
                .status(topico.getStatus())
                .autor(topico.getAutor() != null ? topico.getAutor().getNombre() : null)
                .curso(topico.getCurso() != null ? topico.getCurso().getNombre() : null)
                .build();
    }
}
