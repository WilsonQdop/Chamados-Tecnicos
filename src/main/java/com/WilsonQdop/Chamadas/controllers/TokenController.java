package com.WilsonQdop.Chamadas.controllers;

import com.WilsonQdop.Chamadas.dtos.tokendto.LoginRequestDTO;
import com.WilsonQdop.Chamadas.dtos.tokendto.LoginResponseDTO;
import com.WilsonQdop.Chamadas.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody LoginRequestDTO login) {
        LoginResponseDTO token = this.tokenService.login(login);
        return ResponseEntity.ok(token);
    }
}
