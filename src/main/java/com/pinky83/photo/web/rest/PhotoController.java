package com.pinky83.photo.web.rest;

import com.pinky83.photo.Repo;
import com.pinky83.photo.repository.PhotoRepository;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("photo")
public class PhotoController {

    @Inject @Repo
    private PhotoRepository photoRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getPhotos () {
        return   Json.createObjectBuilder().add("тестовое фото: ", photoRepository.find(100002).toString()).build();
    }
}
