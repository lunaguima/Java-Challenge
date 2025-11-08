package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.util.ArrayList;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public ArrayList<UsuarioTO> findAll(){
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findAll();
    }

    public UsuarioTO findById(Integer id){
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findByCodigo(id);
    }

    public UsuarioTO save(UsuarioTO usuario){
        usuarioDAO = new UsuarioDAO();


        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            System.out.println("Erro: Seu nome não pode estar vazio, digite um nome , por favor.");
            return null;
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@") || !usuario.getEmail().contains(".")) {
            System.out.println("Erro: Email inválido.");
            return null;
        }
        if (usuario.getSenha() == null || usuario.getSenha().length() < 8) {
            System.out.println("Erro: Sua senha deve ter no mínimo 8 caracteres.");
            return null;
        }
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            System.out.println("Erro: Seu username não pode ser vazio!");
            return null;
        }


        if (usuario.getIdade() < 18 || usuario.getIdade() > 120) {
            System.out.println("Erro: Sua idade deve ser maior de 18 anos.");
            return null;
        }

        String username = usuario.getUsername().trim();
        if (!username.startsWith("@")) {
            username = "@" + username;
        }
        usuario.setUsername(username);

        usuario.setNome(usuario.getNome().trim());
        usuario.setEmail(usuario.getEmail().trim());


        if (usuarioDAO.findByEmail(usuario.getEmail()) != null) {
            System.out.println("Erro: Esse e-mail já foi cadastrado.");
            return null;
        }
        if (usuarioDAO.findByUsername(usuario.getUsername()) != null) {
            System.out.println("Erro: Esse username já está em uso.");
            return null;
        }
        return usuarioDAO.save(usuario);
    }


    public boolean delete(Integer id){
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.delete(id);
    }


    public UsuarioTO update(UsuarioTO usuario){
        usuarioDAO = new UsuarioDAO();


        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            System.out.println("Erro: Seu nome não pode estar vazio, digite um nome , por favor.");
            return null;
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@") || !usuario.getEmail().contains(".")) {
            System.out.println("Erro: Email inválido.");
            return null;
        }

        if (usuario.getSenha() == null || usuario.getSenha().length() < 8) {
            System.out.println("Erro: Sua senha deve ter no mínimo 8 caracteres.");
            return null;
        }
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            System.out.println("Erro: Seu username não pode ser vazio!");
            return null;
        }


        if (usuario.getIdade() < 18 || usuario.getIdade() > 120) {
            System.out.println("Erro: Sua idade deve ser maior de 18 anos.");
            return null;
        }

        String username = usuario.getUsername().trim();
        if (!username.startsWith("@")) {
            username = "@" + username;
        }
        usuario.setUsername(username);
        usuario.setNome(usuario.getNome().trim());
        usuario.setEmail(usuario.getEmail().trim());


        UsuarioTO usuarioPorEmail = usuarioDAO.findByEmail(usuario.getEmail());
        if (usuarioPorEmail != null && usuarioPorEmail.getIdUsuario() != usuario.getIdUsuario()) {
            System.out.println("Erro: O email já está em uso por outro usuário.");
            return null;
        }

        UsuarioTO usuarioPorUsername = usuarioDAO.findByUsername(usuario.getUsername());
        if (usuarioPorUsername != null && usuarioPorUsername.getIdUsuario() != usuario.getIdUsuario()) {
            System.out.println("Erro: O username já está em uso por outro usuário.");
            return null;
        }
        return usuarioDAO.update(usuario);
    }


    public UsuarioTO login(String login, String senha) {
        usuarioDAO = new UsuarioDAO();

        if (login == null || senha == null || login.trim().isEmpty() || senha.trim().isEmpty()) {
            return null;
        }
        login = login.trim();


        if (!login.contains("@") && !login.contains(".")) {
            login = "@" + login;
        }

        UsuarioTO usuario = usuarioDAO.findByLogin(login, senha);

        if (usuario == null) {
            System.out.println("Erro: O login ou a senha estão inválidos.");
            return null;
        }
        return usuario;
    }
}