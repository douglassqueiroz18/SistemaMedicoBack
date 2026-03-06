package com.clinica.medica.service;

import com.clinica.medica.entity.Usuario;
import com.clinica.medica.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Usuario salvar(Usuario usuario) {
        // Validação de segurança no Back-end
        if (usuario.getSenha() == null || usuario.getSenha().length() < 6) {
            throw new RuntimeException("A senha deve conter no mínimo 6 caracteres.");
        }

        if (repository.existsByLogin(usuario.getLogin())) {
            throw new RuntimeException("Este login já está em uso!");
        }
        
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return repository.save(usuario);
        }

        public List<Usuario> listarTodos() {
            return repository.findAll();
        }
        public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }
    public Usuario atualizar(Long id, Usuario dadosNovos) {
        // 1. Busca o usuário atual no banco
        Usuario usuarioBanco = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 2. Atualiza os campos básicos
        usuarioBanco.setNome(dadosNovos.getNome());
        usuarioBanco.setLogin(dadosNovos.getLogin());
        usuarioBanco.setPerfil(dadosNovos.getPerfil());

        // 3. Lógica da Senha: Só criptografa e salva se o campo não estiver vazio
        if (dadosNovos.getSenha() != null && !dadosNovos.getSenha().trim().isEmpty()) {
            usuarioBanco.setSenha(encoder.encode(dadosNovos.getSenha()));
        }
        // Se vier vazio (como mandamos no Angular no patchValue), o hibernate ignora e mantém a senha antiga.

        return repository.save(usuarioBanco);
    }
    }