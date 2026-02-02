package com.juansaul.web.controller;

import com.juansaul.application.service.RespuestaService;
import com.juansaul.web.dto.DatosActualizarRespuesta;
import com.juansaul.web.dto.DatosRegistroRespuesta;
import com.juansaul.web.dto.DatosRespuestaRespuesta;
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
@RequestMapping("/respuestas")
@RequiredArgsConstructor
public class RespuestaController {

    private final RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> registrar(
            @RequestBody @Valid DatosRegistroRespuesta datos,
            UriComponentsBuilder uriBuilder) {
        
        DatosRespuestaRespuesta response = respuestaService.registrar(datos);
        URI url = uriBuilder.path("/respuestas/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaRespuesta>> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {
        return ResponseEntity.ok(respuestaService.listar(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaRespuesta> detallar(@PathVariable Long id) {
        return ResponseEntity.ok(respuestaService.detallar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaRespuesta> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarRespuesta datos) {
        return ResponseEntity.ok(respuestaService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        respuestaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
