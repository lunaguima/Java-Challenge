package br.com.fiap.bo;

import br.com.fiap.dao.VideoDAO;
import br.com.fiap.to.VideoTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class VideoBO {

    private VideoDAO videoDAO;

    public ArrayList<VideoTO> findAll(){
        videoDAO = new VideoDAO();
        return videoDAO.findAll();
    }

    public ArrayList<VideoTO> findByUsuarioId(Integer idUsuario) {
        videoDAO = new VideoDAO();

        return videoDAO.findByUsuario(idUsuario);
    }

    public VideoTO findById(Integer id){
        videoDAO = new VideoDAO();

        return videoDAO.findByCodigo(id);
    }

    public VideoTO save(VideoTO video) {
        videoDAO = new VideoDAO();

        if (video.getTitulo() == null || video.getTitulo().trim().isEmpty()) {
            System.out.println("Erro em salvar vídeo: O título não pode ser vazio.");
            return null;
        }
        if (video.getLinkVideo() == null || video.getLinkVideo().trim().isEmpty()) {
            System.out.println("Erro em salvar vídeo: O link do vídeo não pode ser vazio.");
            return null;
        }
        if (video.getIdUsuario() == 0) {
            System.out.println("Erro em salvar: O vídeo não está vinculado a um usuário.");
            return null;
        }

        video.setDataVideo(LocalDate.now());
        video.setTitulo(video.getTitulo().trim());
        if(video.getDescricao() != null) {
            video.setDescricao(video.getDescricao().trim());
        }

        return videoDAO.save(video);
    }

    public boolean delete(Integer idVideo, Integer idUsuarioLogado) {
        videoDAO = new VideoDAO();

        VideoTO video = videoDAO.findByCodigo(idVideo);
        if (video == null) {
            System.out.println("Falha ao excluir: Vídeo com ID " + idVideo + " não encontrado.");
            return false;
        }

        if (video.getIdUsuario() != idUsuarioLogado) {
            System.out.println("Falha ao excluir: Usuário " + idUsuarioLogado + " não tem permissão para excluir o vídeo " + idVideo + ".");
            return false;
        }

        return videoDAO.delete(idVideo);
    }

    public VideoTO update(VideoTO video, Integer idUsuarioLogado) {
        videoDAO = new VideoDAO();

        if (video.getTitulo() == null || video.getTitulo().trim().isEmpty()) {
            System.out.println("Falha ao atualizar vídeo: O título não pode ser vazio.");
            return null;
        }
        if (video.getLinkVideo() == null || video.getLinkVideo().trim().isEmpty()) {
            System.out.println("Falha ao atualizar vídeo: O link do vídeo não pode ser vazio.");
            return null;
        }

        VideoTO videoOriginal = videoDAO.findByCodigo(video.getIdVideo());
        if (videoOriginal == null) {
            System.out.println("Falha ao atualizar: Vídeo com ID " + video.getIdVideo() + " não encontrado.");
            return null;
        }

        if (videoOriginal.getIdUsuario() != idUsuarioLogado) {
            System.out.println("Falha ao atualizar: Usuário " + idUsuarioLogado + " não tem permissão para editar o vídeo " + video.getIdVideo() + ".");
            return null;
        }

        video.setIdUsuario(videoOriginal.getIdUsuario());
        video.setDataVideo(videoOriginal.getDataVideo());
        video.setTitulo(video.getTitulo().trim());
        if(video.getDescricao() != null) {
            video.setDescricao(video.getDescricao().trim());
        }

        return videoDAO.update(video);
    }
}