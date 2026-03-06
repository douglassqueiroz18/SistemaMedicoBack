package com.clinica.medica.service;

import com.clinica.medica.DTO.LoginDTO;
import com.clinica.medica.entity.Usuario;
import com.clinica.medica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario autenticar(LoginDTO dados) {
        // Busca no banco
        Usuario usuario = repository.findByLogin(dados.getLogin()).orElse(null);
        // Compara a senha digitada com a criptografada do banco
        if (usuario != null && passwordEncoder.matches(dados.getSenha(), usuario.getSenha())) {
            return usuario; // Sucesso!
        }

        throw new RuntimeException("Usuário ou senha inválidos");
    }
}