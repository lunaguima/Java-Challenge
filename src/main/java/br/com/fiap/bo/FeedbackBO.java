package br.com.fiap.bo;

import br.com.fiap.dao.FeedbackDAO;
import br.com.fiap.to.FeedbackTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class FeedbackBO {

    private FeedbackDAO feedbackDAO;

    public ArrayList<FeedbackTO> findAll(){
        feedbackDAO = new FeedbackDAO();
        return feedbackDAO.findAll();
    }

    public ArrayList<FeedbackTO> findByUsuarioId(Integer idUsuario) {
        feedbackDAO = new FeedbackDAO();
        return feedbackDAO.findByUsuario(idUsuario);
    }

    public FeedbackTO findById(Integer id){
        feedbackDAO = new FeedbackDAO();
        return feedbackDAO.findByCodigo(id);
    }

    public FeedbackTO save(FeedbackTO feedback) {
        feedbackDAO = new FeedbackDAO();

        if (feedback.getComentario() == null || feedback.getComentario().trim().isEmpty()) {
            System.out.println("Erro ao salvar feedback: O comentário não pode ser vazio.");
            return null;
        }

        if (feedback.getAvaliacao() < 1 || feedback.getAvaliacao() > 5) {
            System.out.println("Erro ao salvar feedback: A avaliação deve ser um número entre 1 e 5.");
            return null;
        }
        if (feedback.getIdUsuario() == 0) {
            System.out.println("Erro ao salvar: O feedback não está vinculado a um usuário.");
            return null;
        }

        feedback.setDataCriacao(LocalDate.now());
        feedback.setComentario(feedback.getComentario().trim());

        return feedbackDAO.save(feedback);
    }

    public boolean delete(Integer idFeedback, Integer idUsuarioLogado) {
        feedbackDAO = new FeedbackDAO();

        FeedbackTO feedback = feedbackDAO.findByCodigo(idFeedback);
        if (feedback == null) {
            System.out.println("Erro ao excluir: Feedback com o ID " + idFeedback + " não foi encontrado.");
            return false;
        }

        if (feedback.getIdUsuario() != idUsuarioLogado) {
            System.out.println("Falha ao excluir: O usuário " + idUsuarioLogado + " não tem permissão para excluir esse feedback " + idFeedback + ".");
            return false;
        }
        return feedbackDAO.delete(idFeedback);
    }

    public FeedbackTO update(FeedbackTO feedback, Integer idUsuarioLogado) {
        feedbackDAO = new FeedbackDAO();

        if (feedback.getComentario() == null || feedback.getComentario().trim().isEmpty()) {
            System.out.println("Falha ao atualizar feedback: O comentário não pode ser vazio.");
            return null;
        }
        if (feedback.getAvaliacao() < 1 || feedback.getAvaliacao() > 5) {
            System.out.println("Falha ao atualizar feedback: A avaliação deve ser um número entre 1 e 5.");
            return null;
        }

        FeedbackTO feedbackOriginal = feedbackDAO.findByCodigo(feedback.getIdFeedback());
        if (feedbackOriginal == null) {
            System.out.println("Falha ao atualizar: Feedback com ID " + feedback.getIdFeedback() + " não encontrado.");
            return null;
        }

        if (feedbackOriginal.getIdUsuario() != idUsuarioLogado) {
            System.out.println("Falha ao atualizar: Usuário " + idUsuarioLogado + " não tem permissão para editar o feedback " + feedback.getIdFeedback() + ".");
            return null;
        }

        feedback.setIdUsuario(feedbackOriginal.getIdUsuario());
        feedback.setDataCriacao(feedbackOriginal.getDataCriacao());
        feedback.setComentario(feedback.getComentario().trim());

        return feedbackDAO.update(feedback);
    }
}