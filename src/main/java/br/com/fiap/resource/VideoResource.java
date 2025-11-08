package br.com.fiap.resource;

import br.com.fiap.bo.VideoBO;
import br.com.fiap.to.VideoTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/java/video")
public class VideoResource {

    private VideoBO videoBO = new VideoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<VideoTO> resultado = videoBO.findAll();
        Response.ResponseBuilder response = null;


        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id) {
        VideoTO resultado = videoBO.findById(id);
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
    public Response save(@Valid VideoTO video, @PathParam("idUsuarioLogado") Integer idUsuarioLogado) {


        video.setIdUsuario(idUsuarioLogado);

        VideoTO resultado = videoBO.save(video);
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
    @Path("/{idVideo}/usuario/{idUsuarioLogado}")
    public Response delete(@PathParam("idVideo") Integer idVideo, @PathParam("idUsuarioLogado") Integer idUsuarioLogado) {

        Response.ResponseBuilder response = null;


        if (videoBO.delete(idVideo, idUsuarioLogado)) {
            response = Response.status(204); // 204 - NO CONTENT
        } else {

            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{idVideo}/usuario/{idUsuarioLogado}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid VideoTO video, @PathParam("idVideo") Integer idVideo,@PathParam("idUsuarioLogado") Integer idUsuarioLogado) {

        video.setIdVideo(idVideo);

        VideoTO resultado = videoBO.update(video, idUsuarioLogado);
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