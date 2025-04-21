package br.com.carlos.pais_back.DTO;

public record UsuarioAutenticado(
        String login,
        String nome,
        String token,
        boolean administrador,
        boolean autenticado
) {}
