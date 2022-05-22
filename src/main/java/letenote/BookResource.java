package letenote;

import letenote.model.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/books")
public class BookResource {
    private final static List<Book> books= new ArrayList<>();
    static {
       var newBook = Book.builder()
        .id(UUID.randomUUID().toString())
        .title("Quarkus Cookbook: Kubernetes-Optimized Java Solutions")
        .description("Optimized for Kubernetes, Quarkus is designed to help you create Java applications that are cloud first, container native, and serverless capable.")
        .author("Alex Soto")
        .createdAt(System.currentTimeMillis())
        .build();

       books.add(newBook);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks(){
        return books;
    }
}
