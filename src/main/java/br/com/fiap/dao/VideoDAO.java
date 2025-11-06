package br.com.fiap.dao;

import br.com.fiap.to.VideoTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VideoDAO {

    public VideoTO save(VideoTO video){
        String sql = "insert into t_ddd_video(id_video, id_usuario, ds_titulo, ds_descricao, ds_link_video, nr_duracao, nm_categoria, dt_video) " +
                "values (seq_video.nextval, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setInt(1, video.getIdUsuario());
            ps.setString(2, video.getTitulo());
            ps.setString(3, video.getDescricao());
            ps.setString(4, video.getLinkVideo());
            ps.setString(5, video.getDuracao());
            ps.setString(6, video.getCategoria());
            ps.setDate(7, Date.valueOf(video.getDataVideo()));

            if (ps.executeUpdate() > 0) {
                return video;
            } else {
                return null;
            }
        } catch (SQLException e){
            System.out.println("Erro ao salvar vídeo: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Integer idVideo){
        String sql = "delete from t_ddd_video where id_video = ? ";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setInt(1, idVideo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir vídeo: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public VideoTO update(VideoTO video){
        String sql = "update t_ddd_video set id_usuario = ?, ds_titulo = ?, ds_descricao = ?, ds_link_video = ?, nr_duracao = ?, nm_categoria = ?, dt_video = ? where id_video = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setInt(1, video.getIdUsuario());
            ps.setString(2, video.getTitulo());
            ps.setString(3, video.getDescricao());
            ps.setString(4, video.getLinkVideo());
            ps.setString(5, video.getDuracao());
            ps.setString(6, video.getCategoria());
            ps.setDate(7, Date.valueOf(video.getDataVideo()));
            ps.setInt(8, video.getIdVideo());

            if (ps.executeUpdate() > 0){
                return video;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar vídeo: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ArrayList<VideoTO> findAll() {
        ArrayList<VideoTO> videos = new ArrayList<VideoTO>();
        String sql = "select * from t_ddd_video order by id_video";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                VideoTO video = new VideoTO();
                video.setIdVideo(rs.getInt("id_video"));
                video.setIdUsuario(rs.getInt("id_usuario"));
                video.setTitulo(rs.getString("ds_titulo"));
                video.setDescricao(rs.getString("ds_descricao"));
                video.setLinkVideo(rs.getString("ds_link_video"));
                video.setDuracao(rs.getString("nr_duracao"));
                video.setCategoria(rs.getString("nm_categoria"));
                video.setDataVideo(rs.getDate("dt_video").toLocalDate());

                videos.add(video);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta de vídeos: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return videos;
    }

    public VideoTO findByCodigo(Integer idVideo) {
        String sql = "select * from t_ddd_video where id_video = ?";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setInt(1, idVideo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                VideoTO video = new VideoTO();
                video.setIdVideo(rs.getInt("id_video"));
                video.setIdUsuario(rs.getInt("id_usuario"));
                video.setTitulo(rs.getString("ds_titulo"));
                video.setDescricao(rs.getString("ds_descricao"));
                video.setLinkVideo(rs.getString("ds_link_video"));
                video.setDuracao(rs.getString("nr_duracao"));
                video.setCategoria(rs.getString("nm_categoria"));
                video.setDataVideo(rs.getDate("dt_video").toLocalDate());
                return video;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta do vídeo: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }


    public ArrayList<VideoTO> findByUsuario(Integer idUsuario) {
        ArrayList<VideoTO> videos = new ArrayList<VideoTO>();
        String sql = "select * from t_ddd_video where id_usuario = ? order by id_video";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setInt(1, idUsuario); // WHERE
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VideoTO video = new VideoTO();
                video.setIdVideo(rs.getInt("id_video"));
                video.setIdUsuario(rs.getInt("id_usuario"));
                video.setTitulo(rs.getString("ds_titulo"));
                video.setDescricao(rs.getString("ds_descricao"));
                video.setLinkVideo(rs.getString("ds_link_video"));
                video.setDuracao(rs.getString("nr_duracao"));
                video.setCategoria(rs.getString("nm_categoria"));
                video.setDataVideo(rs.getDate("dt_video").toLocalDate());
                videos.add(video);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta de vídeos por usuário: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return videos;
    }
}