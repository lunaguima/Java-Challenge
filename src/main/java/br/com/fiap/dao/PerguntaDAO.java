package br.com.fiap.dao;

import br.com.fiap.to.PerguntaTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PerguntaDAO {



    public PerguntaTO save(PerguntaTO pergunta) {
        String sql = "insert into t_ddd_pergunta(id_pergunta, id_usuario, ds_titulo, ds_descricao, dt_criacao) values (seq_pergunta.nextval, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, pergunta.getIdUsuario());
            ps.setString(2, pergunta.getTitulo());
            ps.setString(3, pergunta.getDescricao());
            ps.setDate(4, Date.valueOf(pergunta.getDataCriacao()));

            if (ps.executeUpdate() > 0) {
                return pergunta;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar a pergunta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Integer idPergunta) {
        String sql = "delete from t_ddd_pergunta where id_pergunta = ? ";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idPergunta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir a pergunta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public PerguntaTO update(PerguntaTO pergunta) {
        String sql = "update t_ddd_pergunta set id_usuario = ?, ds_titulo = ?, ds_descricao = ?, dt_criacao = ? where id_pergunta = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, pergunta.getIdUsuario());
            ps.setString(2, pergunta.getTitulo());
            ps.setString(3, pergunta.getDescricao());
            ps.setDate(4, Date.valueOf(pergunta.getDataCriacao()));
            ps.setInt(5, pergunta.getIdPergunta());

            if (ps.executeUpdate() > 0) {
                return pergunta;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro em atualizar pergunta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ArrayList<PerguntaTO> findAll() {
        ArrayList<PerguntaTO> perguntas = new ArrayList<>();
        String sql = "select * from t_ddd_pergunta order by id_pergunta";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PerguntaTO pergunta = new PerguntaTO();
                pergunta.setIdPergunta(rs.getInt("id_pergunta"));
                pergunta.setIdUsuario(rs.getInt("id_usuario"));
                pergunta.setTitulo(rs.getString("ds_titulo"));
                pergunta.setDescricao(rs.getString("ds_descricao"));
                pergunta.setDataCriacao(rs.getDate("dt_criacao").toLocalDate());
                perguntas.add(pergunta);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta das perguntas: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return perguntas;
    }


    public PerguntaTO findByCodigo(Integer idPergunta) {

        String sql = "select * from t_ddd_pergunta where id_pergunta = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idPergunta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                PerguntaTO pergunta = new PerguntaTO();
                pergunta.setIdPergunta(rs.getInt("id_pergunta"));
                pergunta.setIdUsuario(rs.getInt("id_usuario"));
                pergunta.setTitulo(rs.getString("ds_titulo"));
                pergunta.setDescricao(rs.getString("ds_descricao"));
                pergunta.setDataCriacao(rs.getDate("dt_criacao").toLocalDate());
                return pergunta;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta da pergunta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }


    public ArrayList<PerguntaTO> findByUsuario(Integer idUsuario) {
        ArrayList<PerguntaTO> perguntas = new ArrayList<>();
        String sql = "SELECT * FROM t_ddd_pergunta WHERE id_usuario = ? ORDER BY dt_criacao DESC";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                PerguntaTO pergunta = new PerguntaTO();
                pergunta.setIdPergunta(rs.getInt("id_pergunta"));
                pergunta.setIdUsuario(rs.getInt("id_usuario"));
                pergunta.setTitulo(rs.getString("ds_titulo"));
                pergunta.setDescricao(rs.getString("ds_descricao"));
                pergunta.setDataCriacao(rs.getDate("dt_criacao").toLocalDate());
                perguntas.add(pergunta);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta de perguntas por usuário: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return perguntas;
    }
}