package br.com.fiap.bo;

import br.com.fiap.dao.PerguntaDAO;
import br.com.fiap.to.PerguntaTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class PerguntaBO {

    private PerguntaDAO perguntaDAO;

    public ArrayList<PerguntaTO> findAll(){
        perguntaDAO = new PerguntaDAO();
        return perguntaDAO.findAll();
    }

    public ArrayList<PerguntaTO> findByUsuarioId(Integer idUsuario){
        perguntaDAO = new PerguntaDAO();
        return perguntaDAO.findByUsuario(idUsuario);
    }

    public PerguntaTO findById(Integer id){
        perguntaDAO = new PerguntaDAO();
        return perguntaDAO.findByCodigo(id);
    }

    public PerguntaTO save(PerguntaTO pergunta) {
        perguntaDAO = new PerguntaDAO();

        if (pergunta.getTitulo() == null || pergunta.getTitulo().trim().isEmpty()) {
            System.out.println("Erro em salvar pergunta: O título não pode ser vazio.");
            return null;
        }
        if (pergunta.getDescricao() == null || pergunta.getDescricao().trim().isEmpty()) {
            System.out.println("Erro em salvar pergunta: A descrição não pode ser vazia.");
            return null;
        }
        if (pergunta.getIdUsuario() == 0) {
            System.out.println("Erro em salvar: A pergunta não está vinculada a um usuário.");
            return null;
        }

        pergunta.setDataCriacao(LocalDate.now());

        pergunta.setTitulo(pergunta.getTitulo().trim());
        pergunta.setDescricao(pergunta.getDescricao().trim());

        return perguntaDAO.save(pergunta);
    }

    public boolean delete(Integer idPergunta, Integer idUsuarioLogado) {
        perguntaDAO = new PerguntaDAO();

        PerguntaTO pergunta = perguntaDAO.findByCodigo(idPergunta);
        if (pergunta == null) {
            System.out.println("Falha ao excluir: A Pergunta com o ID " + idPergunta + " não foi encontrada.");
            return false;
        }

        if (pergunta.getIdUsuario() != idUsuarioLogado) {
            System.out.println("Falha ao excluir: O usuário " + idUsuarioLogado + " não tem permissão para excluir essa pergunta " + idPergunta + ".");
            return false;
        }
        return perguntaDAO.delete(idPergunta);
    }

    public PerguntaTO update(PerguntaTO pergunta, Integer idUsuarioLogado) {
        perguntaDAO = new PerguntaDAO();

        if (pergunta.getTitulo() == null || pergunta.getTitulo().trim().isEmpty()) {
            System.out.println("Falha em atualizar: O título não pode estar vazio.");
            return null;
        }
        if (pergunta.getDescricao() == null || pergunta.getDescricao().trim().isEmpty()) {
            System.out.println("Falha em atualizar: A descrição não pode ser vazia.");
            return null;
        }

        PerguntaTO perguntaOriginal = perguntaDAO.findByCodigo(pergunta.getIdPergunta());

        if (perguntaOriginal == null) {
            System.out.println("Falha em atualizar: A Pergunta com o ID " + pergunta.getIdPergunta() + " não encontrada.");
            return null;
        }

        if (perguntaOriginal.getIdUsuario() != idUsuarioLogado) {
            System.out.println("Falha em atualizar: O usuário " + idUsuarioLogado + " não tem permissão para editar essa pergunta " + pergunta.getIdPergunta() + ".");
            return null;
        }

        pergunta.setIdUsuario(perguntaOriginal.getIdUsuario());
        pergunta.setDataCriacao(perguntaOriginal.getDataCriacao());

        pergunta.setTitulo(pergunta.getTitulo().trim());
        pergunta.setDescricao(pergunta.getDescricao().trim());

        return perguntaDAO.update(pergunta);
    }
}