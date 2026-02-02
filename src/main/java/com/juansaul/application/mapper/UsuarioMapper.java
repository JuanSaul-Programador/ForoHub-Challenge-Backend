package com.juansaul.application.mapper;

import com.juansaul.domain.model.Usuario;
import com.juansaul.web.dto.DatosRegistroUsuario;
import com.juansaul.web.dto.DatosRespuestaUsuario;

public interface UsuarioMapper {
    
    Usuario toEntity(DatosRegistroUsuario dto, String encodedPassword);
    
    DatosRespuestaUsuario toDto(Usuario usuario);
}
