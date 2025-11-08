package br.com.fiap.to;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

public class FeedbackTO {

    @PositiveOrZero
    private int idFeedback;

    @PositiveOrZero
    private int idUsuario;

    @NotBlank
    private String comentario;

    @PositiveOrZero
    private int avaliacao;

    @PastOrPresent
    private LocalDate dataCriacao;

    public FeedbackTO() {
    }

    public FeedbackTO(int idFeedback, int idUsuario, String comentario, int avaliacao, LocalDate dataCriacao) {
        this.idFeedback = idFeedback;
        this.idUsuario = idUsuario;
        this.comentario = comentario;
        this.avaliacao = avaliacao;
        this.dataCriacao = dataCriacao;
    }

    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
