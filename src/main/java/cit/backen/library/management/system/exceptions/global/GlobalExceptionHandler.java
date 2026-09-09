package cit.backen.library.management.system.exceptions.global;


import cit.backen.library.management.system.api.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException exception){
        List<Map<String,String>> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f-> Map.of("field",f.getField(),
                        "message",f.getDefaultMessage()))
                .toList();
        ApiResponse<Object> apiResponse = new ApiResponse<>("BAD_REQUEST","Validation error",Map.of("errors",errors));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
