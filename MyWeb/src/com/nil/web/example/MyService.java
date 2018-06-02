package com.nil.web.example; /**
 * Created by liorr on 5/28/18.
 */


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

@Path("/")
public class MyService {
    private static final boolean GLOBAL_DICTIONARY = false; // true means all sessions access the same Dictionary
    private static final String SESSION_ATTR_NAME = MyService.class.getName() + "_Dictionary";
    @Context
    private HttpServletRequest httpRequest;
    @Context
    private HttpServletResponse httpResponse;

    private final Dictionary mDictionary = new Dictionary();

    public MyService(final Map<Class, Object> env) {
        // env provides access to ServletConfig and ServletContext.
    }

    private Dictionary getDictionary(HttpSession session) {
        if (GLOBAL_DICTIONARY) { // all sessions access the same Dictionary
            return mDictionary;
        } else { // each session gets its own Dictionary
            Dictionary dictionary = (Dictionary) session.getAttribute(SESSION_ATTR_NAME);
            if (dictionary == null) {
                session.setAttribute(SESSION_ATTR_NAME, dictionary = new Dictionary());
            }
            return dictionary;
        }
    }

    /**
     * Here we declare how the meaning for a word can be accessed.
     * E.g.:  http://server:port/dictionry?word=hello
     *
     * @param key {@link String} word to be lookup in the dictionary
     * @return the meaning of the word.
     */
    @GET
    @Path("/dictionary/{word}")
    @Produces("text/plain")
    public Response lookup(@PathParam("word") String key) {
//        return Response.ok(getDictionary(httpRequest.getSession()).lookup(key)).build();
        return Response.ok("lior").build();
    }

    @GET
    @Path("/dictionary")
    @Produces("application/octet-stream")
    public Response content() {
        httpResponse.setHeader("Content-Disposition", "attachment;filename=dictionary.json");
        try {
            getDictionary(httpRequest.getSession()).serialize(httpResponse.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }

    @POST
    @Path("/dictionary/{word}{meaning}")
    @Produces("text/plain")
    public Response enter(@FormParam("word") String key, @FormParam("meaning") String value) {
        getDictionary(httpRequest.getSession()).enter(key, value);
        return Response.ok("OK").build();
    }

    @DELETE
    @Path("/dictionary/{word}")
    @Produces("text/plain")
    public Response delete(@PathParam("word") String key) {
        getDictionary(httpRequest.getSession()).delete(key);
        return Response.ok("OK").build();
    }
}
