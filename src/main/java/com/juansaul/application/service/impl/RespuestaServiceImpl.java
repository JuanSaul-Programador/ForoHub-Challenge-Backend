package com.juansaul.application.service.impl;

import com.juansaul.application.mapper.RespuestaMapper;
import com.juansaul.application.service.RespuestaService;
import com.juansaul.domain.model.Respuesta;
import com.juansaul.domain.model.Topico;
import com.juansaul.domain.model.Usuario;
import com.juansaul.domain.repository.RespuestaRepository;
import com.juansaul.domain.repository.TopicoRepository;
import com.juansaul.domain.repository.UsuarioRepository;
import com.juansaul.web.dto.DatosActualizarRespuesta;
import com.juansaul.web.dto.DatosRegistroRespuesta;
import com.juansaul.web.dto.DatosRespuestaRespuesta;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RespuestaServiceImpl implements RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RespuestaMapper respuestaMapper;

    @Override
    @Transactional
    public DatosRespuestaRespuesta registrar(DatosRegistroRespuesta datos) {
        Topico topico = topicoRepository.findById(datos.idTopico())
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con ID: " + datos.idTopico()));

        Usuario autor = usuarioRepository.findById(datos.idAutor())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + datos.idAutor()));

        Respuesta respuesta = respuestaMapper.toEntity(datos, topico, autor);
        respuestaRepository.save(respuesta);
        
        return respuestaMapper.toDto(respuesta);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DatosRespuestaRespuesta> listar(Pageable paginacion) {
        return respuestaRepository.findAll(paginacion)
                .map(respuestaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public DatosRespuestaRespuesta detallar(Long id) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Respuesta no encontrada con ID: " + id));
        return respuestaMapper.toDto(respuesta);
    }

    @Override
    @Transactional
    public DatosRespuestaRespuesta actualizar(Long id, DatosActualizarRespuesta datos) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Respuesta no encontrada con ID: " + id));
        
        Respuesta respuestaActualizada = Respuesta.builder()
                .id(respuesta.getId())
                .mensaje(datos.mensaje())
                .topico(respuesta.getTopico())
                .fechaCreacion(respuesta.getFechaCreacion())
                .autor(respuesta.getAutor())
                .solucion(datos.solucion())
                .build();
        
        respuestaRepository.save(respuestaActualizada);
        return respuestaMapper.toDto(respuestaActualizada);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!respuestaRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Respuesta no encontrada con ID: " + id);
        }
        respuestaRepository.deleteById(id);
    }
}
