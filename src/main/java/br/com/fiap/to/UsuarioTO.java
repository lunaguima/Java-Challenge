package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class UsuarioTO {

    @PositiveOrZero
    private int idUsuario;

    @NotBlank
    private String nome;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @PositiveOrZero
    private int idade;

    public UsuarioTO() {}

    public UsuarioTO(int idUsuario, String nome, String username, String email, String senha, int idade) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
