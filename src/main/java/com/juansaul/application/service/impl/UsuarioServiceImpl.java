package com.juansaul.application.service.impl;

import com.juansaul.application.mapper.UsuarioMapper;
import com.juansaul.application.service.UsuarioService;
import com.juansaul.domain.model.Usuario;
import com.juansaul.domain.repository.UsuarioRepository;
import com.juansaul.web.dto.DatosActualizarUsuario;
import com.juansaul.web.dto.DatosRegistroUsuario;
import com.juansaul.web.dto.DatosRespuestaUsuario;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public DatosRespuestaUsuario registrar(DatosRegistroUsuario datos) {
        if (usuarioRepository.existsByEmail(datos.email())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + datos.email());
        }
        
        String encodedPassword = passwordEncoder.encode(datos.contrasena());
        Usuario usuario = usuarioMapper.toEntity(datos, encodedPassword);
        usuarioRepository.save(usuario);
        
        return usuarioMapper.toDto(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DatosRespuestaUsuario> listar(Pageable paginacion) {
        return usuarioRepository.findAll(paginacion)
                .map(usuarioMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public DatosRespuestaUsuario detallar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        return usuarioMapper.toDto(usuario);
    }

    @Override
    @Transactional
    public DatosRespuestaUsuario actualizar(Long id, DatosActualizarUsuario datos) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        String nuevaContrasena = usuario.getContrasena();
        if (datos.contrasena() != null && !datos.contrasena().isBlank()) {
            nuevaContrasena = passwordEncoder.encode(datos.contrasena());
        }

        String nuevoNombre = usuario.getNombre();
        if (datos.nombre() != null && !datos.nombre().isBlank()) {
            nuevoNombre = datos.nombre();
        }

        Usuario usuarioActualizado = Usuario.builder()
                .id(usuario.getId())
                .nombre(nuevoNombre)
                .email(usuario.getEmail()) // Email no se actualiza
                .contrasena(nuevaContrasena)
                .build();

        usuarioRepository.save(usuarioActualizado);
        return usuarioMapper.toDto(usuarioActualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
