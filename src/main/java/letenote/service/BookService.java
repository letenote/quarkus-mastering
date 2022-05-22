package letenote.service;

import letenote.model.Book;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookService {
    @Inject
    Validator validator;
    @ApplicationScoped
    public Book bookValidator(Book book){
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        boolean isValid = violations.size() != 0;
        if(isValid){
            List<String> getErrors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());

            throw new IllegalArgumentException(String.join(", ", getErrors));
        }
        return book;
    }
}
