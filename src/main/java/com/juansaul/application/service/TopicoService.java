package com.juansaul.application.service;

import com.juansaul.web.dto.DatosRegistroTopico;
import com.juansaul.web.dto.DatosRegistroTopicoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicoService {

    DatosRegistroTopicoResponse registrar(DatosRegistroTopico datos);

    Page<DatosRegistroTopicoResponse> listar(String curso, Integer anio, Pageable paginacion);

    DatosRegistroTopicoResponse detallar(Long id);

    void eliminar(Long id);
}