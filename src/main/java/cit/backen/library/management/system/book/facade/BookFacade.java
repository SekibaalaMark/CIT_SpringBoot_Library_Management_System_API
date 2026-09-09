package cit.backen.library.management.system.book.facade;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.book.dto.BookPartialUpdateRequest;
import cit.backen.library.management.system.book.dto.BookRequest;
import cit.backen.library.management.system.book.dto.BookResponse;
import cit.backen.library.management.system.book.mapper.BookMapper;
import cit.backen.library.management.system.book.model.Book;
import cit.backen.library.management.system.book.repository.BookRepository;
import cit.backen.library.management.system.exceptions.book.BookNotFoundException;
import cit.backen.library.management.system.exceptions.book.BookWithIsbnAlreadyExists;
import cit.backen.library.management.system.page.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookFacade {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookFacade(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }


    public ApiResponse<BookResponse> addBook(BookRequest request){
        if(bookRepository.existsByIsbn(request.getIsbn())){
            throw new BookWithIsbnAlreadyExists(request.getIsbn());
        }
        Book book = bookRepository.save(bookMapper.bookRequestToModel(request));
        BookResponse bookResponse = bookMapper.bookModelToResponse(book);
        return new ApiResponse<>("SUCCESS","Book added successfully",bookResponse);
    }

    public ApiResponse<PageResponse<BookResponse>> getAllBooks(int page,int pageSize){
        int zeroBasedPage = Math.max(0,page-1);
        Pageable pageable = PageRequest.of(zeroBasedPage,pageSize, Sort.by("id").ascending());
        Page<Book> bookPage = bookRepository.findAll(pageable);
        List<BookResponse> bookResponseList = bookPage.getContent()
                .stream()
                .map(bookMapper::bookModelToResponse)
                .toList();

        PageResponse<BookResponse> pageResponse = new PageResponse<>(
                bookResponseList,
                page,
                pageSize,
                bookPage.getTotalElements(),
                bookPage.getTotalPages(),
                bookPage.isLast()
        );
        return new ApiResponse<>("SUCCESS","Page of Books",pageResponse);
    }


    public ApiResponse<BookResponse> getBookById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("id: "+ id));
        BookResponse bookResponse = bookMapper.bookModelToResponse(book);
        return new ApiResponse<>("SUCCESS","Book returned successfully",bookResponse);
    }


    public ApiResponse<BookResponse> updateBookFully(Long id, BookRequest bookRequest){
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("id: "+ id));

        book.setTitle(bookRequest.getTitle());
        book.setStatus(bookRequest.getStatus());
        book.setIsbn(bookRequest.getIsbn());
        book.setEdition(bookRequest.getEdition());
        book.setAuthor(bookRequest.getAuthor());

        BookResponse bookResponse =  bookMapper.bookModelToResponse(bookRepository.save(book));
        return new ApiResponse<>("SUCCESS","Book Fully Updated Successfully",bookResponse);
    }


    public ApiResponse<BookResponse> updateBookPartial(Long id, BookPartialUpdateRequest request){
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("id: "+id));
        if(request.author() != null){
            book.setAuthor(request.author());
        }
        if(request.edition() != null){
            book.setEdition(request.edition());
        }
        if(request.title() != null){
            book.setTitle(request.title());
        }
        if(request.isbn() != null){
            book.setIsbn(request.isbn());
        }
        if(request.status()!= null){
            book.setStatus(request.status());
        }
        BookResponse bookResponse = bookMapper.bookModelToResponse(bookRepository.save(book));
        return  new ApiResponse<>("SUCCESS","Book Updated successfully",bookResponse);
    }
}
