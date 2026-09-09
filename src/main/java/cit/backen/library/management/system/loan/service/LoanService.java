package cit.backen.library.management.system.loan.service;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.exceptions.loan.LoanNotFoundException;
import cit.backen.library.management.system.loan.dto.LoanRequest;
import cit.backen.library.management.system.loan.dto.LoanResponse;
import cit.backen.library.management.system.loan.enums.Status;
import cit.backen.library.management.system.loan.facade.LoanFacade;
import cit.backen.library.management.system.loan.mapper.LoanMapper;
import cit.backen.library.management.system.loan.model.Loan;
import cit.backen.library.management.system.loan.repository.LoanRepository;;
import cit.backen.library.management.system.page.response.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    public ApiResponse<LoanResponse> addLoan(LoanRequest request) {
        return loanFacade.addLoan(request);
    }


    public ApiResponse<PageResponse<LoanResponse>> getAllLoans(int page, int pageSize){
        return loanFacade.getAllLoans(page,pageSize);
    }

    public ApiResponse<LoanResponse> settleLoan(Long id){
        Loan loan = loanRepository.findById(id)
                .orElseThrow(()-> new LoanNotFoundException("id: "+id));
        loan.setStatus(Status.SETTLED);
        LoanResponse loanResponse = loanMapper.loanModelToResponse(loanRepository.save(loan));
        return new ApiResponse<>("SUCCESS","Loan settled successfully",loanResponse);
    }

    public ApiResponse<Object> deleteLoanById(Long id){
        loanRepository.findById(id)
                .orElseThrow(()-> new LoanNotFoundException("id: "+id));
        loanRepository.deleteById(id);
        return new ApiResponse<>("SUCCESS","Loan record deleted successfully",null);
    }
}
