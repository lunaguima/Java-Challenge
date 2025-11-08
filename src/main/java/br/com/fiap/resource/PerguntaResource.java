package br.com.fiap.resource;

import br.com.fiap.bo.PerguntaBO;
import br.com.fiap.to.PerguntaTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/java/pergunta")
public class PerguntaResource {

    private PerguntaBO perguntaBO = new PerguntaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<PerguntaTO> resultado = perguntaBO.findAll();
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
        PerguntaTO resultado = perguntaBO.findById(id);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Path("/usuario/{idUsuarioLogado}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid PerguntaTO pergunta,
                         @PathParam("idUsuarioLogado") Integer idUsuarioLogado) {

        pergunta.setIdUsuario(idUsuarioLogado);

        PerguntaTO resultado = perguntaBO.save(pergunta);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null); // 201 - CREATED
        } else {

            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{idPergunta}/usuario/{idUsuarioLogado}")
    public Response delete(@PathParam("idPergunta") Integer idPergunta,
                           @PathParam("idUsuarioLogado") Integer idUsuarioLogado) {

        Response.ResponseBuilder response = null;

        if (perguntaBO.delete(idPergunta, idUsuarioLogado)) {
            response = Response.status(204); // 204 - NO CONTENT
        } else {

            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }


    @PUT
    @Path("/{idPergunta}/usuario/{idUsuarioLogado}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid PerguntaTO pergunta, @PathParam("idPergunta") Integer idPergunta, @PathParam("idUsuarioLogado") Integer idUsuarioLogado) {


        pergunta.setIdPergunta(idPergunta);

        PerguntaTO resultado = perguntaBO.update(pergunta, idUsuarioLogado);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 201 - Ok
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }
}