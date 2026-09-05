package cit.backen.library.management.system.book.mapper;

import cit.backen.library.management.system.book.dto.BookRequest;
import cit.backen.library.management.system.book.dto.BookResponse;
import cit.backen.library.management.system.book.enums.Status;
import cit.backen.library.management.system.book.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book bookRequestToModel(BookRequest request){
        Book book = new Book();
        book.setAuthor(request.getAuthor());
        book.setEdition(request.getEdition());
        book.setIsbn(request.getIsbn());
        book.setStatus(request.getStatus());
        book.setTitle(request.getTitle());
        return book;
    }

    public BookResponse bookModelToResponse(Book book){
        return new BookResponse(book.getId(),book.getIsbn(),book.getTitle(),book.getAuthor(),book.getEdition(),book.getStatus());
    }

}
