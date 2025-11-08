package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.UsuarioTO;

import br.com.fiap.to.LoginTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/java/usuario")
public class UsuarioResource {

    private UsuarioBO usuarioBO = new UsuarioBO();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid LoginTO loginData) {


        UsuarioTO resultado = usuarioBO.login(loginData.getLogin(), loginData.getSenha());
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
            response.entity(resultado);
        } else {
            response = Response.status(Response.Status.UNAUTHORIZED); // 401 (Não Autorizado)
            response.entity("Login ou senha inválidos.");
        }
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid UsuarioTO usuario) {

        UsuarioTO resultado = usuarioBO.save(usuario);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null); // 201 - CREATED
        } else {

            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<UsuarioTO> resultado = usuarioBO.findAll();
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id) {
        UsuarioTO resultado = usuarioBO.findById(id);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{idUsuarioLogado}")
    public Response delete(@PathParam("idUsuarioLogado") Integer idUsuarioLogado) {
        Response.ResponseBuilder response = null;

        if (usuarioBO.delete(idUsuarioLogado)) {
            response = Response.status(204); // 204 - NO CONTENT
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{idUsuarioLogado}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid UsuarioTO usuario, @PathParam("idUsuarioLogado") Integer idUsuarioLogado) {
        usuario.setIdUsuario(idUsuarioLogado);

        UsuarioTO resultado = usuarioBO.update(usuario);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 - Ok
        } else {

            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }
}