package com.juansaul.web.controller;

import com.juansaul.application.service.UsuarioService;
import com.juansaul.web.dto.DatosActualizarUsuario;
import com.juansaul.web.dto.DatosRegistroUsuario;
import com.juansaul.web.dto.DatosRespuestaUsuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrar(
            @RequestBody @Valid DatosRegistroUsuario datos,
            UriComponentsBuilder uriBuilder) {
        
        DatosRespuestaUsuario response = usuarioService.registrar(datos);
        URI url = uriBuilder.path("/usuarios/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaUsuario>> listar(
            @PageableDefault(size = 10, sort = "nombre") Pageable paginacion) {
        return ResponseEntity.ok(usuarioService.listar(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> detallar(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.detallar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarUsuario datos) {
        return ResponseEntity.ok(usuarioService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
