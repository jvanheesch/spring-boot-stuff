package com.github.jvanheesch.boot.jersey;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Controller
@Path("/rule")
public class JaxRsEndpoint {

    @GET
    @Path("/world")
    public Response test() {
        return Response.status(Status.OK).entity("jax ws").build();
    }

    /**
     * curl --header "Content-Type: application/json" --request POST --data '{"someString": "abc"}' localhost:8080/jersey/rule/post
     */
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postEndpoint(MyEntity myEntity) {
        return Response.status(Status.OK).entity(myEntity).build();
    }

    @GET
    @Path("getPath")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") Long id) {
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
