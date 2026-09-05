package cit.backen.library.management.system.book.controller;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.book.dto.BookRequest;
import cit.backen.library.management.system.book.dto.BookResponse;
import cit.backen.library.management.system.book.service.BookService;
import cit.backen.library.management.system.page.response.PageResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookResponse>> addBook(@Valid @RequestBody BookRequest request){
        ApiResponse<BookResponse> apiResponse = bookService.addBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<BookResponse>>> getAllBooks(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        ApiResponse<PageResponse<BookResponse>> apiResponse = bookService.getAllBooks(page,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> getBookById(@PathVariable Long id){
        ApiResponse<BookResponse> apiResponse = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }


}
