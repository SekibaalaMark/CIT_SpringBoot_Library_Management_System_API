package cit.backen.library.management.system.loan.controller;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.loan.dto.LoanRequest;
import cit.backen.library.management.system.loan.dto.LoanResponse;
import cit.backen.library.management.system.loan.service.LoanService;
import cit.backen.library.management.system.page.response.PageResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LoanResponse>> addLoan(@Valid @RequestBody LoanRequest request){
        ApiResponse<LoanResponse> apiResponse = loanService.addLoan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<LoanResponse>>> getAllLoans(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        ApiResponse<PageResponse<LoanResponse>> apiResponse = loanService.getAllLoans(page,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PatchMapping("/settle/{id}")
    public ResponseEntity<ApiResponse<LoanResponse>> settleLoan(@PathVariable Long id){
        ApiResponse<LoanResponse> apiResponse = loanService.settleLoan(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
