package com.github.jvanheesch.boot.jersey;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/consumes-not-ignored")
public class JaxRsConsumesNotIngoredEndpoint {

    /**
     * Works without "Content-Type: application/json" header.
     * curl localhost:8080/jersey/consumes-not-ignored?id=10
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") Long id) {
        return Response.ok().entity(new MyEntity(String.valueOf(id))).build();
    }

    /**
     * Works without "Content-Type: application/json" header.
     * curl --request DELETE localhost:8080/jersey/consumes-not-ignored?id=10
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") Long id) {
        return Response.ok().entity(new MyEntity(String.valueOf(id))).build();
    }

    /**
     * Does NOT work without "Content-Type: application/json" header.
     * curl --header "Content-Type: application/json" --request POST --data '{"someString": "abc"}' --request POST localhost:8080/jersey/consumes-not-ignored?id=10
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(@QueryParam("id") Long id) {
        return Response.ok().entity(new MyEntity(String.valueOf(id))).build();
    }

    /**
     * Works without "Content-Type: application/json" header.
     * curl --request PUT localhost:8080/jersey/consumes-not-ignored?id=10
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(@QueryParam("id") Long id) {
        return Response.ok().entity(new MyEntity(String.valueOf(id))).build();
    }

    private static class MyEntity {
        private String someString;

        public MyEntity() {
        }

        public MyEntity(String someString) {
            this.someString = someString;
        }

        @JsonProperty
        public String getSomeString() {
            return this.someString;
        }

        public void setSomeString(String someString) {
            this.someString = someString;
        }
    }
}
