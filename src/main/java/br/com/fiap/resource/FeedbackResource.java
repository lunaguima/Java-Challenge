package br.com.fiap.resource;

import br.com.fiap.bo.FeedbackBO;
import br.com.fiap.to.FeedbackTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/java/feedback")
public class FeedbackResource {

    private FeedbackBO feedbackBO = new FeedbackBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<FeedbackTO> resultado = feedbackBO.findAll();
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
    @Path("/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByUsuario(@PathParam("idUsuario") Integer idUsuario) {
        ArrayList<FeedbackTO> resultado = feedbackBO.findByUsuarioId(idUsuario);
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
    public Response save(@Valid FeedbackTO feedback, @PathParam("idUsuarioLogado") Integer idUsuarioLogado) {

        feedback.setIdUsuario(idUsuarioLogado);

        FeedbackTO resultado = feedbackBO.save(feedback);
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
    @Path("/{idFeedback}/usuario/{idUsuarioLogado}")
    public Response delete(@PathParam("idFeedback") Integer idFeedback, @PathParam("idUsuarioLogado") Integer idUsuarioLogado) {

        Response.ResponseBuilder response = null;

        if (feedbackBO.delete(idFeedback, idUsuarioLogado)) {
            response = Response.status(204); // 204 - NO CONTENT
        } else {

            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{idFeedback}/usuario/{idUsuarioLogado}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid FeedbackTO feedback,@PathParam("idFeedback") Integer idFeedback, @PathParam("idUsuarioLogado") Integer idUsuarioLogado) {

        feedback.setIdFeedback(idFeedback);

        FeedbackTO resultado = feedbackBO.update(feedback, idUsuarioLogado);
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