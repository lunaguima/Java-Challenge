package br.com.fiap.to;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

public class PerguntaTO {

    @PositiveOrZero
    private int idPergunta;

    @PositiveOrZero
    private int idUsuario;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @PastOrPresent
    private LocalDate dataCriacao;

    public PerguntaTO(){
    }

    public PerguntaTO(int idPergunta, int idUsuario, String titulo, String descricao, LocalDate dataCriacao) {
        this.idPergunta = idPergunta;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
