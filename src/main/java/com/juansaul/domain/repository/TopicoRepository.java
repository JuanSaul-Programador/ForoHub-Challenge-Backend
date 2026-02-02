package com.juansaul.domain.repository;

import com.juansaul.domain.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    
    Page<Topico> findByCursoNombreContainingIgnoreCase(String cursoNombre, Pageable pageable);
    
    @Query("SELECT t FROM Topico t WHERE YEAR(t.fechaCreacion) = :anio")
    Page<Topico> findByFechaCreacionYear(@Param("anio") Integer anio, Pageable pageable);
    
    @Query("SELECT t FROM Topico t WHERE LOWER(t.curso.nombre) LIKE LOWER(CONCAT('%', :curso, '%')) AND YEAR(t.fechaCreacion) = :anio")
    Page<Topico> findByCursoNombreAndFechaCreacionYear(@Param("curso") String curso, @Param("anio") Integer anio, Pageable pageable);
}
