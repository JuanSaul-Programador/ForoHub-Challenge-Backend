package com.juansaul.domain.repository;

import com.juansaul.domain.model.Respuesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    
    Page<Respuesta> findByTopicoId(Long topicoId, Pageable pageable);
}
