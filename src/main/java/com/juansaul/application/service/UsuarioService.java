package com.juansaul.application.service;

import com.juansaul.web.dto.DatosActualizarUsuario;
import com.juansaul.web.dto.DatosRegistroUsuario;
import com.juansaul.web.dto.DatosRespuestaUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    DatosRespuestaUsuario registrar(DatosRegistroUsuario datos);

    Page<DatosRespuestaUsuario> listar(Pageable paginacion);

    DatosRespuestaUsuario detallar(Long id);

    DatosRespuestaUsuario actualizar(Long id, DatosActualizarUsuario datos);

    void eliminar(Long id);
}
