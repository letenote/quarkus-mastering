package letenote;

import letenote.model.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;
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
    public Collection<Book> getBooks(){
        return books;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Book createBook(Book book){
        var addBook = Book.builder()
                .id(UUID.randomUUID().toString())
                .title(book.getTitle())
                .description(book.getDescription())
                .author(book.getAuthor())
                .createdAt(System.currentTimeMillis())
                .build();

        books.add(addBook);
        return  addBook;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Book updateBook(@PathParam("id") String id, Book book){
        String getIndex = null;
        for (int i = 0; i < books.size(); i++) {
            if( books.get(i).getId().equals(id) ) {
                getIndex = String.valueOf(i);
            }
        }

        if(getIndex != null ){
            books.remove((int) Integer.parseInt(getIndex));
            var updateBook = Book.builder()
                    .id(id)
                    .title(book.getTitle())
                    .description(book.getDescription())
                    .author(book.getAuthor())
                    .createdAt(book.getCreatedAt())
                    .updatedAt(System.currentTimeMillis())
                    .build();

            books.add(Integer.parseInt(getIndex), updateBook);
            return updateBook;
        }
        return book;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteBook(@PathParam("id") String id){
        String getIndex = null;
        for (int i = 0; i < books.size(); i++) {
            if( books.get(i).getId().equals(id) ) {
                getIndex = String.valueOf(i);
            }
        }
        if(getIndex != null ){
            books.remove((int) Integer.parseInt(getIndex));
            return "Book Removed";
        }
        return String.format("Book with %s not available", id);
    }
}
