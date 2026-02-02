package com.juansaul.web.controller;

import com.juansaul.application.service.CursoService;
import com.juansaul.web.dto.DatosActualizarCurso;
import com.juansaul.web.dto.DatosRegistroCurso;
import com.juansaul.web.dto.DatosRespuestaCurso;
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
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrar(
            @RequestBody @Valid DatosRegistroCurso datos,
            UriComponentsBuilder uriBuilder) {
        
        DatosRespuestaCurso response = cursoService.registrar(datos);
        URI url = uriBuilder.path("/cursos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaCurso>> listar(
            @PageableDefault(size = 10, sort = "nombre") Pageable paginacion) {
        return ResponseEntity.ok(cursoService.listar(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> detallar(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.detallar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarCurso datos) {
        return ResponseEntity.ok(cursoService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
