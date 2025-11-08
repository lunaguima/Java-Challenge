package br.com.fiap.to;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class VideoTO {

    private int idVideo;

    @PositiveOrZero
    private int idUsuario;

    @NotBlank
    private String titulo;

    @NotBlank(message = "A descrição não pode estar vazia.")
    private String descricao;

    @NotBlank(message = "O link do vídeo é obrigatório.")
    private String linkVideo;

    @NotBlank(message = "A duração é obrigatória.")
    private String duracao;

    @NotBlank(message = "A categoria não pode estar vazia.")
    private String categoria;

    @PastOrPresent(message = "A data do vídeo deve ser do presente ou do passado.")
    private LocalDate dataVideo;

    public VideoTO() {
    }

    public VideoTO(int idVideo, int idUsuario, String titulo, String descricao, String linkVideo, String duracao, String categoria, LocalDate dataVideo) {
        this.idVideo = idVideo;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.descricao = descricao;
        this.linkVideo = linkVideo;
        this.duracao = duracao;
        this.categoria = categoria;
        this.dataVideo = dataVideo;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
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

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataVideo() {
        return dataVideo;
    }

    public void setDataVideo(LocalDate dataVideo) {
        this.dataVideo = dataVideo;
    }
}