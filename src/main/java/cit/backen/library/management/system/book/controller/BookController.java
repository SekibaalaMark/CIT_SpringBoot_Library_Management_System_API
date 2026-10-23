package cit.backen.library.management.system.book.controller;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.book.dto.BookPartialUpdateRequest;
import cit.backen.library.management.system.book.dto.BookRequest;
import cit.backen.library.management.system.book.dto.BookResponse;
import cit.backen.library.management.system.book.service.BookService;
import cit.backen.library.management.system.page.response.PageResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@EnableMethodSecurity
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
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

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<ApiResponse<BookResponse>> updateBookFully(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest){
        ApiResponse<BookResponse> apiResponse = bookService.updateBookFully(id,bookRequest);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<ApiResponse<BookResponse>> updateBookPartial(@PathVariable Long id, @Valid @RequestBody BookPartialUpdateRequest request){
        ApiResponse<BookResponse> apiResponse = bookService.updateBookPartial(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<ApiResponse<BookResponse>> getBookByIsbn(@PathVariable String isbn){
        ApiResponse<BookResponse> apiResponse = bookService.getBookByIsbn(isbn);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<ApiResponse<Object>> deleteBookById(@PathVariable Long id){
        ApiResponse<Object> apiResponse = bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
