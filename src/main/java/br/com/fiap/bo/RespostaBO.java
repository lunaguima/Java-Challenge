package br.com.fiap.bo;


import br.com.fiap.dao.RespostaDAO;

import br.com.fiap.to.RespostaTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class RespostaBO {

    private RespostaDAO respostaDAO;

    public ArrayList<RespostaTO> findAll(){
        respostaDAO = new RespostaDAO();
        return respostaDAO.findAll();
    }

    public RespostaTO findById(Integer id){
        respostaDAO = new RespostaDAO();
        return respostaDAO.findByCodigo(id);
    }

    public RespostaTO save(RespostaTO resposta) {
        respostaDAO = new RespostaDAO();

        if (resposta.getConteudo() == null || resposta.getConteudo().trim().isEmpty()) {
            System.out.println("Falha ao salvar: O conteúdo da resposta não pode estar vazio!");
            return null;
        }
        if (resposta.getIdPergunta() == 0) {
            System.out.println("Falha em salvar: A resposta não está ligada a uma pergunta.");
            return null;
        }
        if (resposta.getIdUsuario() == 0) {
            System.out.println("Falha em salvar: A resposta não tem um autor.");
            return null;
        }

        resposta.setDataCriacao(LocalDate.now());
        resposta.setConteudo(resposta.getConteudo().trim());

        RespostaTO respostaSalva = respostaDAO.save(resposta);

        return respostaSalva;
    }

    public boolean delete(Integer idResposta, Integer idUsuarioLogado) {
        respostaDAO = new RespostaDAO();

        RespostaTO resposta = respostaDAO.findByCodigo(idResposta);
        if (resposta == null) {
            System.out.println("Falha ao excluir: A Resposta com o ID " + idResposta + " não encontrada.");
            return false;
        }


        if (resposta.getIdUsuario() != idUsuarioLogado) {
            System.out.println("Falha ao excluir: O usuário " + idUsuarioLogado + " não tem permissão para excluir a resposta " + idResposta + ".");
            return false;
        }

        return respostaDAO.delete(idResposta);
    }


    public RespostaTO update(RespostaTO resposta, Integer idUsuarioLogado) {
        respostaDAO = new RespostaDAO();

        if (resposta.getConteudo() == null || resposta.getConteudo().trim().isEmpty()) {
            System.out.println("Falha em atualizar: O conteúdo não pode ser vazio.");
            return null;
        }

        RespostaTO respostaOriginal = respostaDAO.findByCodigo(resposta.getIdResposta());
        if (respostaOriginal == null) {
            System.out.println("Falha ao atualizar: Resposta com o ID " + resposta.getIdResposta() + " não encontrada.");
            return null;
        }

        if (respostaOriginal.getIdUsuario() != idUsuarioLogado) {
            System.out.println("Falha ao atualizar: O Usuário " + idUsuarioLogado + " não tem permissão para editar essa resposta " + resposta.getIdResposta() + ".");
            return null;
        }
        resposta.setIdUsuario(respostaOriginal.getIdUsuario());
        resposta.setIdPergunta(respostaOriginal.getIdPergunta());
        resposta.setDataCriacao(respostaOriginal.getDataCriacao());

        return respostaDAO.update(resposta);
    }

    public ArrayList<RespostaTO> findByPerguntaId(Integer idPergunta){
        respostaDAO = new RespostaDAO();
        return respostaDAO.findByPergunta(idPergunta);
    }

}