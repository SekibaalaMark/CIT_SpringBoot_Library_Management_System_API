package cit.backen.library.management.system.exceptions.global;

import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.exceptions.book.BookNotFoundException;
import cit.backen.library.management.system.exceptions.member.MemberNotFoundException;
import cit.backen.library.management.system.exceptions.member.MemberWithNinNumberAlreadyExistException;
import cit.backen.library.management.system.exceptions.member.MemberWithUsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class MemberGlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleMemberNotFoundException(MemberNotFoundException exception){
        ApiResponse<Object> apiResponse = new ApiResponse<>("NOT_FOUND", exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(MemberWithUsernameAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleMemberWithUsernameAlreadyExistsException(MemberWithUsernameAlreadyExistsException exception){
        ApiResponse<Object> apiResponse = new ApiResponse<>("BAD_REQUEST", exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }


    @ExceptionHandler(MemberWithNinNumberAlreadyExistException.class)
    public ResponseEntity<ApiResponse<Object>> handleMemberWithNinNumberAlreadyExistException(MemberWithNinNumberAlreadyExistException exception){
        ApiResponse<Object> apiResponse = new ApiResponse<>("BAD_REQUEST", exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }




}

