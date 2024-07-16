package com.alura.topicos.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.alura.topicos.exceptions.UnauthorizedException;
import com.alura.topicos.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {
    @GetMapping("/auth")
    public User login(@RequestParam("user") String username, @RequestParam("password") String password) {
        // Aquí deberías validar las credenciales (username y password) con tu base de datos u otro sistema de autenticación

        // Ejemplo de validación básica (simulada)
        if (!isValidUser(username, password)) {
            throw new UnauthorizedException("Credenciales inválidas");
        }

        String token = getJWTToken(username);
        User user = new User();
        user.setUser(username);
        user.setToken(token);
        return user;
    }

    private boolean isValidUser(String username, String password) {
        // Implementa tu lógica de validación de usuario aquí (puede ser con una base de datos, servicio externo, etc.)
        // Este es un ejemplo básico que siempre devuelve true para cualquier usuario
        return true;
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000)) // Token válido por 10 minutos
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
