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
            System.out.println("Falha ao salvar pergunta: O título não pode ser vazio.");
            return null;
        }
        if (pergunta.getDescricao() == null || pergunta.getDescricao().trim().isEmpty()) {
            System.out.println("Falha ao salvar pergunta: A descrição não pode ser vazia.");
            return null;
        }
        if (pergunta.getIdUsuario() == 0) {
            System.out.println("Falha ao salvar: A pergunta não está vinculada a um usuário.");
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
            System.out.println("Falha ao excluir: Pergunta com ID " + idPergunta + " não encontrada.");
            return false;
        }

        if (pergunta.getIdUsuario() != idUsuarioLogado) {
            System.out.println("Falha ao excluir: Usuário " + idUsuarioLogado + " não tem permissão para excluir a pergunta " + idPergunta + ".");
            return false;
        }

        return perguntaDAO.delete(idPergunta);
    }

    public PerguntaTO update(PerguntaTO pergunta, Integer idUsuarioLogado) {
        perguntaDAO = new PerguntaDAO();

        if (pergunta.getTitulo() == null || pergunta.getTitulo().trim().isEmpty()) {
            System.out.println("Falha ao atualizar: O título não pode estar vazio.");
            return null;
        }
        if (pergunta.getDescricao() == null || pergunta.getDescricao().trim().isEmpty()) {
            System.out.println("Falha ao atualizar: A descrição não pode estar vazia.");
            return null;
        }

        PerguntaTO perguntaOriginal = perguntaDAO.findByCodigo(pergunta.getIdPergunta());

        if (perguntaOriginal == null) {
            System.out.println("Falha ao atualizar: Pergunta com ID " + pergunta.getIdPergunta() + " não encontrada.");
            return null;
        }

        if (perguntaOriginal.getIdUsuario() != idUsuarioLogado) {
            System.out.println("Falha ao atualizar: Usuário " + idUsuarioLogado + " não tem permissão para editar a pergunta " + pergunta.getIdPergunta() + ".");
            return null;
        }

        pergunta.setIdUsuario(perguntaOriginal.getIdUsuario());
        pergunta.setDataCriacao(perguntaOriginal.getDataCriacao());

        pergunta.setTitulo(pergunta.getTitulo().trim());
        pergunta.setDescricao(pergunta.getDescricao().trim());

        return perguntaDAO.update(pergunta);
    }
}