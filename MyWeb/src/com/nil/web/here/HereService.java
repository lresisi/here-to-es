package com.nil.web.here;

/**
 * Created by liorr on 5/28/18.
 */

import com.nil.test.ElasticsearchClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/")
public class HereService {
    @Context
    private HttpServletRequest httpRequest;

    @Context
    private HttpServletResponse httpResponse;

    private final SimpleDictionary mDictionary = new SimpleDictionary();
    private ElasticsearchClient client = new ElasticsearchClient("localhost", 9200);

    public HereService(final Map<Class, Object> env) {
        // env provides access to ServletConfig and ServletContext.
    }


    /**
     * Here we declare how the meaning for a word can be accessed.
     * E.g.:  http://server:port/dictionry?word=hello
     *
     * @param key {@link String} word to be lookup in the dictionary
     * @return the meaning of the word.
     */
    @GET
    @Path("/here/{word}")
    @Produces("text/plain")
    public Response lookup(@PathParam("word") String key) {
        return Response.ok(mDictionary).build();
//        try {
//            return Response.ok(client.get("here_data")).build();
//        } catch (IOException e) {
//            return Response.ok("Error in ES").build();
//        }
    }

    @POST
    @Path("/here/{word}{meaning}")
    @Produces("text/plain")
    public Response enter(@FormParam("word") String key, @FormParam("meaning") String value) {
        mDictionary.enter(key, value);
        return Response.ok("OK").build();
    }

    @POST
    @Path("/here/insert")
    @Consumes(MediaType.APPLICATION_XML)
    public Response insert(RideOffer rideOffer) {
        String result = "RideOffer created: " + rideOffer;
        return Response.ok(result).build();
    }
}
