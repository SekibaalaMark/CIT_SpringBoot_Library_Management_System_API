package cit.backen.library.management.system.loan.service;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.loan.dto.LoanRequest;
import cit.backen.library.management.system.loan.dto.LoanResponse;
import cit.backen.library.management.system.loan.facade.LoanFacade;
import cit.backen.library.management.system.loan.mapper.LoanMapper;
import cit.backen.library.management.system.loan.model.Loan;
import cit.backen.library.management.system.loan.repository.LoanRepository;;
import cit.backen.library.management.system.page.response.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class LoanService {
    private final LoanFacade loanFacade;
    private final LoanMapper loanMapper;
    private final LoanRepository loanRepository;

    public LoanService(LoanFacade loanFacade, LoanMapper loanMapper, LoanRepository loanRepository) {
        this.loanFacade = loanFacade;
        this.loanMapper = loanMapper;
        this.loanRepository = loanRepository;
    }

    public ApiResponse<LoanResponse> addLoan(LoanRequest request){
        Loan loan = loanMapper.loanRequestToModel(request);
        LoanResponse loanResponse = loanMapper.loanModelToResponse(loanRepository.save(loan));
        return new ApiResponse<>("SUCCESS","Loan Added Successfully",loanResponse);
    }


    public ApiResponse<PageResponse<LoanResponse>> getAllLoans(int page, int pageSize){
        return loanFacade.getAllLoans(page,pageSize);
    }
}
