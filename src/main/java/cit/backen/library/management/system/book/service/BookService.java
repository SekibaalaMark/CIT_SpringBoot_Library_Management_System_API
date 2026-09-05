package cit.backen.library.management.system.book.service;

import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.book.dto.BookRequest;
import cit.backen.library.management.system.book.dto.BookResponse;
import cit.backen.library.management.system.book.mapper.BookMapper;
import cit.backen.library.management.system.book.model.Book;
import cit.backen.library.management.system.book.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }


    public ApiResponse<BookResponse> addBook(BookRequest request){
        Book book = bookRepository.save(bookMapper.bookRequestToModel(request));
        BookResponse bookResponse = bookMapper.bookModelToResponse(book);
        return new ApiResponse<>("SUCCESS","Book added successfully",bookResponse);    }
}
