package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    public UsuarioTO save(UsuarioTO usuario){
        String sql = "insert into t_ddd_usuario(id_usuario, nm_usuario, nm_username, ds_email, ds_senha, nr_idade) values (seq_usuario.nextval, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1,usuario.getNome());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setInt(5, usuario.getIdade());

            if (ps.executeUpdate() > 0) {
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException e){
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Integer idUsuario){
        String sql = "delete from t_ddd_usuario where id_usuario = ? ";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public UsuarioTO update(UsuarioTO usuario){
        String sql = "update t_ddd_usuario set nm_usuario = ?, nm_username = ?, ds_email = ?, ds_senha = ?, nr_idade = ? where id_usuario = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setInt(5, usuario.getIdade());
            ps.setInt(6, usuario.getIdUsuario());
            if (ps.executeUpdate() > 0){
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ArrayList<UsuarioTO> findAll() {
        ArrayList<UsuarioTO> usuarios = new ArrayList<UsuarioTO>();
        String sql = "select * from t_ddd_usuario order by id_usuario";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                UsuarioTO usuario = new UsuarioTO();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nm_usuario"));
                usuario.setUsername(rs.getString("nm_username"));
                usuario.setEmail(rs.getString("ds_email"));
                usuario.setSenha(rs.getString("ds_senha"));
                usuario.setIdade(rs.getInt("nr_idade"));

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuarios;
    }

    public UsuarioTO findByCodigo(Integer idUsuario) {
        String sql = "select * from t_ddd_usuario where id_usuario = ?";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UsuarioTO usuario = new UsuarioTO();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nm_usuario"));
                usuario.setUsername(rs.getString("nm_username"));
                usuario.setEmail(rs.getString("ds_email"));
                usuario.setSenha(rs.getString("ds_senha"));
                usuario.setIdade(rs.getInt("nr_idade"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public UsuarioTO findByEmail(String email) {
        String sql = "select * from t_ddd_usuario where ds_email = ?";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UsuarioTO usuario = new UsuarioTO();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nm_usuario"));
                usuario.setUsername(rs.getString("nm_username"));
                usuario.setEmail(rs.getString("ds_email"));
                usuario.setSenha(rs.getString("ds_senha"));
                usuario.setIdade(rs.getInt("nr_idade"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta do email: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public UsuarioTO findByUsername(String username) {
        String sql = "select * from t_ddd_usuario where nm_username = ?";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UsuarioTO usuario = new UsuarioTO();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nm_usuario"));
                usuario.setUsername(rs.getString("nm_username"));
                usuario.setEmail(rs.getString("ds_email"));
                usuario.setSenha(rs.getString("ds_senha"));
                usuario.setIdade(rs.getInt("nr_idade"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta do username: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public UsuarioTO findByLogin(String login, String senha) {
        String sql = "select * from t_ddd_usuario where (ds_email = ? OR nm_username = ?) AND ds_senha = ?";

        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setString(1, login);
            ps.setString(2, login);
            ps.setString(3, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UsuarioTO usuario = new UsuarioTO();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nm_usuario"));
                usuario.setUsername(rs.getString("nm_username"));
                usuario.setEmail(rs.getString("ds_email"));
                usuario.setSenha(rs.getString("ds_senha"));
                usuario.setIdade(rs.getInt("nr_idade"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta do login: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}