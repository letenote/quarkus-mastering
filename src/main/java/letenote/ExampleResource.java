package letenote;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/greeting/{your-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String addHello(@PathParam("your-name") String name){
        return "Hello " + name;
    }
}