package com.clinica.medica.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.clinica.medica.entity.Perfil;
import com.clinica.medica.entity.Usuario;
import com.clinica.medica.repository.UsuarioRepository;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Verifica se já existe algum usuário (mestre ou não)
        if (repository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setLogin("admin");
            admin.setNome("Administrador Mestre");
            admin.setPerfil(Perfil.ROLE_ADMIN);
            
            // Criptografando a senha "admin123" usando o algoritmo BCrypt
            admin.setSenha(passwordEncoder.encode("admin123")); 
            
            repository.save(admin);
            
            System.out.println("----------------------------------------------");
            System.out.println("SUCESSO: Usuário mestre criado!");
            System.out.println("Login: admin");
            System.out.println("Senha: admin123 (Criptografada no banco)");
            System.out.println("----------------------------------------------");
        } else {
            System.out.println("INFO: Usuários já existentes no banco. Pulando criação do admin.");
        }
    }
}