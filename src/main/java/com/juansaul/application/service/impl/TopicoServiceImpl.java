package com.juansaul.application.service.impl;

import com.juansaul.application.mapper.TopicoMapper;
import com.juansaul.application.service.TopicoService;
import com.juansaul.domain.model.Topico;
import com.juansaul.domain.repository.CursoRepository;
import com.juansaul.domain.repository.TopicoRepository;
import com.juansaul.domain.repository.UsuarioRepository;
import com.juansaul.web.dto.DatosRegistroTopico;
import com.juansaul.web.dto.DatosRegistroTopicoResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TopicoServiceImpl implements TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final TopicoMapper topicoMapper;

    @Override
    @Transactional
    public DatosRegistroTopicoResponse registrar(DatosRegistroTopico datos) {

        if (topicoRepository.existsByTituloAndMensaje(datos.getTitulo(), datos.getMensaje())) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje");
        }

        var autor = usuarioRepository.findById(datos.getIdAutor())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + datos.getIdAutor()));

        var curso = cursoRepository.findById(datos.getIdCurso())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado con ID: " + datos.getIdCurso()));

        Topico topico = topicoMapper.toEntity(datos, autor, curso);
        topicoRepository.save(topico);
        return topicoMapper.toDto(topico);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DatosRegistroTopicoResponse> listar(String curso, Integer anio, Pageable paginacion) {
        Page<Topico> topicos;
        
        if (curso != null && anio != null) {
            topicos = topicoRepository.findByCursoNombreAndFechaCreacionYear(curso, anio, paginacion);
        } else if (curso != null) {
            topicos = topicoRepository.findByCursoNombreContainingIgnoreCase(curso, paginacion);
        } else if (anio != null) {
            topicos = topicoRepository.findByFechaCreacionYear(anio, paginacion);
        } else {
            topicos = topicoRepository.findAll(paginacion);
        }
        
        return topicos.map(topicoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public DatosRegistroTopicoResponse detallar(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con ID: " + id));

        return topicoMapper.toDto(topico);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Tópico no encontrado con ID: " + id);
        }

        topicoRepository.deleteById(id);
    }
}