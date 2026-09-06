package cit.backen.library.management.system.exceptions.global;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.exceptions.loan.LoanNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LoanGlobalExceptionHandler {

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleLoanNotFoundException(LoanNotFoundException exception){
        ApiResponse<Object> apiResponse = new ApiResponse<>("NOT_FOUND",exception.getMessage(),null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
