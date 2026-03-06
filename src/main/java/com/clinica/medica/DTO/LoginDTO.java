package com.clinica.medica.DTO;

public class LoginDTO {
    private String login;
    private String senha;

    // Construtor vazio (importante para o Spring)
    public LoginDTO() {}

    // Getter para o login - ISSO RESOLVE O ERRO NO AUTHSERVICE
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    // Getter para a senha - ISSO RESOLVE O ERRO NO AUTHSERVICE
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}