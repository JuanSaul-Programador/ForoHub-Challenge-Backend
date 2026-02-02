package com.juansaul.web.controller;

import com.juansaul.domain.model.Usuario;
import com.juansaul.infra.security.TokenService;
import com.juansaul.web.dto.DatosAutenticacion;
import com.juansaul.web.dto.DatosTokenJWT;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosTokenJWT> login(@RequestBody @Valid DatosAutenticacion datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datos.email(),
                datos.contrasena()
        );
        
        Authentication authentication = authenticationManager.authenticate(authToken);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        
        String token = tokenService.generateToken(usuario);
        
        return ResponseEntity.ok(new DatosTokenJWT(token));
    }
}
