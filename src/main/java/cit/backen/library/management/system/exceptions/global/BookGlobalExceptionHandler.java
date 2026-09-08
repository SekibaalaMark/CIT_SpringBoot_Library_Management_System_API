package cit.backen.library.management.system.exceptions.global;

import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.exceptions.book.BookNotFoundException;
import cit.backen.library.management.system.exceptions.book.BookUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class BookGlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleBookNotFoundException(BookNotFoundException exception){
        ApiResponse<Object> apiResponse = new ApiResponse<>("NOT_FOUND", exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(BookUnavailableException.class)
    public ResponseEntity<ApiResponse<Object>> handleBookUnavailableException(BookUnavailableException exception){
        ApiResponse<Object> apiResponse = new ApiResponse<>("BAD_REQUEST", exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
