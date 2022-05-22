package letenote.service;

import letenote.model.Book;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Validator;


public class BookService {
    @Inject
    Validator validator;
    @ApplicationScoped
    public Book bookValidator(Book book){
        validator.validate(book);
        return book;
    }
}
