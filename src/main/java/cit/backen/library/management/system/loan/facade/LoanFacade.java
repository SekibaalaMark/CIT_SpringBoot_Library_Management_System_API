package cit.backen.library.management.system.loan.facade;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.loan.dto.LoanRequest;
import cit.backen.library.management.system.loan.dto.LoanResponse;
import cit.backen.library.management.system.loan.mapper.LoanMapper;
import cit.backen.library.management.system.loan.repository.LoanRepository;
import org.springframework.stereotype.Component;

@Component
public class LoanFacade {
    private final LoanMapper loanMapper;
    private final LoanRepository loanRepository;

    public LoanFacade(LoanMapper loanMapper, LoanRepository loanRepository) {
        this.loanMapper = loanMapper;
        this.loanRepository = loanRepository;
    }

}
