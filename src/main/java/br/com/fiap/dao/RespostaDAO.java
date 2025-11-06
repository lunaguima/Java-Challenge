package br.com.fiap.dao;

import br.com.fiap.to.RespostaTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class RespostaDAO {

    public RespostaTO save(RespostaTO resposta) {

        String sql = "insert into t_ddd_resposta(id_resposta, id_pergunta, id_usuario, ds_conteudo, dt_criacao) values (seq_resposta.nextval, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, resposta.getIdPergunta());
            ps.setInt(2, resposta.getIdUsuario());
            ps.setString(3, resposta.getConteudo());
            ps.setDate(4, Date.valueOf(resposta.getDataCriacao()));

            if (ps.executeUpdate() > 0) {
                return resposta;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar resposta: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public boolean delete(Integer idResposta) {
        String sql = "delete from t_ddd_resposta where id_resposta = ? ";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idResposta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir resposta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public RespostaTO update(RespostaTO resposta) {

        String sql = "update t_ddd_resposta set id_pergunta = ?, id_usuario = ?, ds_conteudo = ?, dt_criacao = ? where id_resposta = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, resposta.getIdPergunta());
            ps.setInt(2, resposta.getIdUsuario());
            ps.setString(3, resposta.getConteudo());
            ps.setDate(4, Date.valueOf(resposta.getDataCriacao()));
            ps.setInt(5, resposta.getIdResposta());

            if (ps.executeUpdate() > 0) {
                return resposta;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar resposta: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public ArrayList<RespostaTO> findAll() {
        ArrayList<RespostaTO> respostas = new ArrayList<>();
        String sql = "select * from t_ddd_resposta order by id_resposta";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RespostaTO resposta = new RespostaTO();
                resposta.setIdResposta(rs.getInt("id_resposta"));
                resposta.setIdPergunta(rs.getInt("id_pergunta"));
                resposta.setIdUsuario(rs.getInt("id_usuario"));
                resposta.setConteudo(rs.getString("ds_conteudo"));
                resposta.setDataCriacao(rs.getDate("dt_criacao").toLocalDate());
                respostas.add(resposta);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta de respostas: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return respostas;
    }


    public RespostaTO findByCodigo(Integer idResposta) {
        String sql = "select * from t_ddd_resposta where id_resposta = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idResposta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                RespostaTO resposta = new RespostaTO();
                resposta.setIdResposta(rs.getInt("id_resposta"));
                resposta.setIdPergunta(rs.getInt("id_pergunta"));
                resposta.setIdUsuario(rs.getInt("id_usuario"));
                resposta.setConteudo(rs.getString("ds_conteudo"));
                resposta.setDataCriacao(rs.getDate("dt_criacao").toLocalDate());
                return resposta;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta de resposta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ArrayList<RespostaTO> findByPergunta(Integer idPergunta) {
        ArrayList<RespostaTO> respostas = new ArrayList<>();
        String sql = "SELECT * FROM t_ddd_resposta WHERE id_pergunta = ? ORDER BY dt_criacao";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idPergunta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RespostaTO resposta = new RespostaTO();
                resposta.setIdResposta(rs.getInt("id_resposta"));
                resposta.setIdPergunta(rs.getInt("id_pergunta"));
                resposta.setIdUsuario(rs.getInt("id_usuario"));
                resposta.setConteudo(rs.getString("ds_conteudo"));
                resposta.setDataCriacao(rs.getDate("dt_criacao").toLocalDate());
                respostas.add(resposta);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta de respostas por pergunta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return respostas;
    }
}