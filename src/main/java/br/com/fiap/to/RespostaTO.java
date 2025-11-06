package br.com.fiap.to;

import java.time.LocalDate;

public class RespostaTO {
    private int idResposta;
    private int idPergunta;
    private int idUsuario;
    private String conteudo;
    private LocalDate dataCriacao;

    public RespostaTO() {
    }

    public RespostaTO(int idResposta, int idPergunta, int idUsuario, String conteudo, LocalDate dataCriacao) {
        this.idResposta = idResposta;
        this.idPergunta = idPergunta;
        this.idUsuario = idUsuario;
        this.conteudo = conteudo;
        this.dataCriacao = dataCriacao;
    }

    public int getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(int idResposta) {
        this.idResposta = idResposta;
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

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
