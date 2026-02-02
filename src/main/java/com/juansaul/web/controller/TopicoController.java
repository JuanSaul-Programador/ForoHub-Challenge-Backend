package com.juansaul.web.controller;

import com.juansaul.application.service.TopicoService;
import com.juansaul.web.dto.DatosActualizarTopico;
import com.juansaul.web.dto.DatosRegistroTopico;
import com.juansaul.web.dto.DatosRegistroTopicoResponse;
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
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosRegistroTopicoResponse> registrar(
            @RequestBody @Valid DatosRegistroTopico datos,
            UriComponentsBuilder uriBuilder) {
        
        DatosRegistroTopicoResponse response = topicoService.registrar(datos);
        
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRegistroTopicoResponse>> listar(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = org.springframework.data.domain.Sort.Direction.ASC) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listar(curso, anio, paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRegistroTopicoResponse> detallar(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.detallar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRegistroTopicoResponse> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos) {
        return ResponseEntity.ok(topicoService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        topicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
