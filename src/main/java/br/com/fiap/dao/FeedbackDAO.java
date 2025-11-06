package br.com.fiap.dao;

import br.com.fiap.to.FeedbackTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackDAO {
    public FeedbackTO save(FeedbackTO feedback) {
        String sql = "insert into t_ddd_feedback (id_feedback, id_usuario, ds_comentario, ds_avaliacao, dt_criacao) values (seq_feedback.nextval, ?, ?, ?, ?)";


        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, feedback.getIdUsuario());
            ps.setString(2, feedback.getComentario());
            ps.setInt(3, feedback.getAvaliacao());
            ps.setDate(4, Date.valueOf(feedback.getDataCriacao()));

            if (ps.executeUpdate() > 0) {
                return feedback;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar feedback: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }
    }


    public boolean delete(Integer idFeedback) {
        String sql = "delete from t_ddd_feedback where id_feedback = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idFeedback);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir feedback: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public FeedbackTO update(FeedbackTO feedback) {
        String sql = "update t_ddd_feedback set id_usuario = ?, ds_comentario = ?, ds_avaliacao = ?, dt_criacao = ? where id_feedback = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, feedback.getIdUsuario());
            ps.setString(2, feedback.getComentario());
            ps.setInt(3, feedback.getAvaliacao());
            ps.setDate(4, Date.valueOf(feedback.getDataCriacao()));
            ps.setInt(5, feedback.getIdFeedback());

            if (ps.executeUpdate() > 0) {
                return feedback;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar feedback: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }
    }


    public ArrayList<FeedbackTO> findAll() {
        ArrayList<FeedbackTO> feedbacks = new ArrayList<>();
        String sql = "select * from t_ddd_feedback order by id_feedback";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FeedbackTO feedback = new FeedbackTO();
                feedback.setIdFeedback(rs.getInt("id_feedback"));
                feedback.setIdUsuario(rs.getInt("id_usuario"));
                feedback.setComentario(rs.getString("ds_comentario"));
                feedback.setAvaliacao(rs.getInt("ds_avaliacao"));
                feedback.setDataCriacao(rs.getDate("dt_criacao").toLocalDate());
                feedbacks.add(feedback);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta de feedbacks: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return feedbacks;
    }

    public FeedbackTO findByCodigo(Integer idFeedback) {
        String sql = "select * from t_ddd_feedback where id_feedback = ?";


        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idFeedback);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                FeedbackTO feedback = new FeedbackTO();
                feedback.setIdFeedback(rs.getInt("id_feedback"));
                feedback.setIdUsuario(rs.getInt("id_usuario"));
                feedback.setComentario(rs.getString("ds_comentario"));
                feedback.setAvaliacao(rs.getInt("ds_avaliacao"));
                feedback.setDataCriacao(rs.getDate("dt_criacao").toLocalDate());
                return feedback;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta de feedback: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }
    }


    public ArrayList<FeedbackTO> findByUsuario(Integer idUsuario) {
        ArrayList<FeedbackTO> feedbacks = new ArrayList<>();
        String sql = "select * from t_ddd_feedback where id_usuario = ? order by id_feedback";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FeedbackTO feedback = new FeedbackTO();
                feedback.setIdFeedback(rs.getInt("id_feedback"));
                feedback.setIdUsuario(rs.getInt("id_usuario"));
                feedback.setComentario(rs.getString("ds_comentario"));
                feedback.setAvaliacao(rs.getInt("ds_avaliacao"));
                feedback.setDataCriacao(rs.getDate("dt_criacao").toLocalDate());
                feedbacks.add(feedback);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta de feedbacks por usuário: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return feedbacks;
    }
}