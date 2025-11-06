package br.com.fiap.to;

import java.time.LocalDate;

public class NotificacaoTO {
    private int idNotificacao;
    private int idUsuario;
    private int idResposta;
    private String mensagem;
    private int lida;
    private String link;
    private LocalDate dataCriacao;

    public NotificacaoTO() {
    }

    public NotificacaoTO(int idNotificacao, int idUsuario, int idResposta, String mensagem, int lida, String link, LocalDate dataCriacao) {
        this.idNotificacao = idNotificacao;
        this.idUsuario = idUsuario;
        this.idResposta = idResposta;
        this.mensagem = mensagem;
        this.lida = lida;
        this.link = link;
        this.dataCriacao = dataCriacao;
    }

    public int getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(int idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(int idResposta) {
        this.idResposta = idResposta;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getLida() {
        return lida;
    }

    public void setLida(int lida) {
        this.lida = lida;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
