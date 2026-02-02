package com.juansaul.application.mapper;

import com.juansaul.domain.model.Curso;
import com.juansaul.domain.model.Topico;
import com.juansaul.domain.model.Usuario;
import com.juansaul.web.dto.DatosRegistroTopico;
import com.juansaul.web.dto.DatosRegistroTopicoResponse;

public interface TopicoMapper {
    Topico toEntity(DatosRegistroTopico dto, Usuario autor, Curso curso);

    DatosRegistroTopicoResponse toDto(Topico topico);
}