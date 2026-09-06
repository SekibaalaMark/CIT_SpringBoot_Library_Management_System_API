package cit.backen.library.management.system.book.service;

import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.book.dto.BookPartialUpdateRequest;
import cit.backen.library.management.system.book.dto.BookRequest;
import cit.backen.library.management.system.book.dto.BookResponse;
import cit.backen.library.management.system.book.facade.BookFacade;
import cit.backen.library.management.system.book.mapper.BookMapper;
import cit.backen.library.management.system.book.model.Book;
import cit.backen.library.management.system.book.repository.BookRepository;
import cit.backen.library.management.system.exceptions.book.BookNotFoundException;
import cit.backen.library.management.system.page.response.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookFacade bookFacade;

    public BookService(BookRepository bookRepository, BookMapper bookMapper, BookFacade bookFacade) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.bookFacade = bookFacade;
    }


    public ApiResponse<BookResponse> addBook(BookRequest request){
        Book book = bookRepository.save(bookMapper.bookRequestToModel(request));
        BookResponse bookResponse = bookMapper.bookModelToResponse(book);
        return new ApiResponse<>("SUCCESS","Book added successfully",bookResponse);
    }


    public ApiResponse<PageResponse<BookResponse>> getAllBooks(int page,int pageSize){
        return bookFacade.getAllBooks(page,pageSize);
    }

    public ApiResponse<BookResponse> getBookById(Long id){
        return bookFacade.getBookById(id);
    }

    public ApiResponse<BookResponse> updateBookFully(Long id, BookRequest bookRequest){
        return bookFacade.updateBookFully(id,bookRequest);
    }

    public ApiResponse<BookResponse> updateBookPartial(Long id, BookPartialUpdateRequest request){
        return bookFacade.updateBookPartial(id,request);
    }

    public ApiResponse<BookResponse> getBookByIsbn(@PathVariable String isbn){
        Book book = bookRepository.getBookByIsbn(isbn)
                .orElseThrow(()-> new BookNotFoundException("ISBN: "+ isbn));
        BookResponse bookResponse = bookMapper.bookModelToResponse(book);
        return new ApiResponse<>("SUCCESS","Book returned",bookResponse);
    }


    public ApiResponse<Object> deleteBookById(Long id){
        bookRepository.findById(id)
                        .orElseThrow(()-> new BookNotFoundException("id: "+id));
        bookRepository.deleteById(id);
        return new ApiResponse<>("SUCCESS","Book deleted successfully",null);
    }

}
