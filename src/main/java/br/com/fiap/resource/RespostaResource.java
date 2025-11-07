package br.com.fiap.resource;

import br.com.fiap.bo.RespostaBO;
import br.com.fiap.to.RespostaTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/java/resposta")
public class RespostaResource {

    private RespostaBO respostaBO = new RespostaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<RespostaTO> resultado = respostaBO.findAll();
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
        RespostaTO resultado = respostaBO.findById(id);
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
    public Response save(@Valid RespostaTO resposta, @PathParam("idUsuarioLogado") Integer idUsuarioLogado) {

        resposta.setIdUsuario(idUsuarioLogado);

        RespostaTO resultado = respostaBO.save(resposta);
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
    @Path("/{idResposta}/usuario/{idUsuarioLogado}")
    public Response delete(@PathParam("idResposta") Integer idResposta, @PathParam("idUsuarioLogado") Integer idUsuarioLogado) {

        Response.ResponseBuilder response = null;

        if (respostaBO.delete(idResposta, idUsuarioLogado)) {
            response = Response.status(204); // 204 - NO CONTENT
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }


    @PUT
    @Path("/{idResposta}/usuario/{idUsuarioLogado}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid RespostaTO resposta, @PathParam("idResposta") Integer idResposta, @PathParam("idUsuarioLogado") Integer idUsuarioLogado) {

        resposta.setIdResposta(idResposta);

        RespostaTO resultado = respostaBO.update(resposta, idUsuarioLogado);
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
    @Path("/pergunta/{idPergunta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPerguntaId(@PathParam("idPergunta") Integer idPergunta){
        ArrayList<RespostaTO> resultado = respostaBO.findByPerguntaId(idPergunta);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }
}