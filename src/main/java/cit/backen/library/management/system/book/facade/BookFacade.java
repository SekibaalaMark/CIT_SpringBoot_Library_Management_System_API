package cit.backen.library.management.system.book.facade;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.book.dto.BookResponse;
import cit.backen.library.management.system.book.mapper.BookMapper;
import cit.backen.library.management.system.book.model.Book;
import cit.backen.library.management.system.book.repository.BookRepository;
import cit.backen.library.management.system.page.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public ApiResponse<PageResponse<BookResponse>> getAllBooks(int page,int pageSize){
        int zeroBasedPage = Math.max(0,page-1);
        Pageable pageable = PageRequest.of(zeroBasedPage,pageSize);
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
}
