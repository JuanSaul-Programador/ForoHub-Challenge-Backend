package com.juansaul.application.mapper.impl;

import com.juansaul.application.mapper.UsuarioMapper;
import com.juansaul.domain.model.Usuario;
import com.juansaul.web.dto.DatosRegistroUsuario;
import com.juansaul.web.dto.DatosRespuestaUsuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(DatosRegistroUsuario dto, String encodedPassword) {
        if (dto == null) return null;
        
        return Usuario.builder()
                .nombre(dto.nombre())
                .email(dto.email())
                .contrasena(encodedPassword)
                .build();
    }

    @Override
    public DatosRespuestaUsuario toDto(Usuario usuario) {
        if (usuario == null) return null;
        
        return new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }
}
