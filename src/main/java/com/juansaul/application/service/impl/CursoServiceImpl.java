package com.juansaul.application.service.impl;

import com.juansaul.application.mapper.CursoMapper;
import com.juansaul.application.service.CursoService;
import com.juansaul.domain.model.Curso;
import com.juansaul.domain.repository.CursoRepository;
import com.juansaul.web.dto.DatosActualizarCurso;
import com.juansaul.web.dto.DatosRegistroCurso;
import com.juansaul.web.dto.DatosRespuestaCurso;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    @Override
    @Transactional
    public DatosRespuestaCurso registrar(DatosRegistroCurso datos) {
        Curso curso = cursoMapper.toEntity(datos);
        cursoRepository.save(curso);
        return cursoMapper.toDto(curso);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DatosRespuestaCurso> listar(Pageable paginacion) {
        return cursoRepository.findAll(paginacion)
                .map(cursoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public DatosRespuestaCurso detallar(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado con ID: " + id));
        return cursoMapper.toDto(curso);
    }

    @Override
    @Transactional
    public DatosRespuestaCurso actualizar(Long id, DatosActualizarCurso datos) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado con ID: " + id));
        
        Curso cursoActualizado = Curso.builder()
                .id(curso.getId())
                .nombre(datos.nombre())
                .categoria(datos.categoria())
                .build();
        
        cursoRepository.save(cursoActualizado);
        return cursoMapper.toDto(cursoActualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Curso no encontrado con ID: " + id);
        }
        cursoRepository.deleteById(id);
    }
}
