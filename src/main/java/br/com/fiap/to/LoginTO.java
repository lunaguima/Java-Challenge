package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class LoginTO {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    public LoginTO() {
    }

    public LoginTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}