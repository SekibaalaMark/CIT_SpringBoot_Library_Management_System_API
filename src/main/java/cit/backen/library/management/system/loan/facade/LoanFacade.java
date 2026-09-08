package cit.backen.library.management.system.loan.facade;


import cit.backen.library.management.system.api.response.ApiResponse;
import cit.backen.library.management.system.exceptions.loan.MemberHasThreeActiveLoans;
import cit.backen.library.management.system.loan.dto.LoanRequest;
import cit.backen.library.management.system.loan.dto.LoanResponse;
import cit.backen.library.management.system.loan.enums.Status;
import cit.backen.library.management.system.loan.mapper.LoanMapper;
import cit.backen.library.management.system.loan.model.Loan;
import cit.backen.library.management.system.loan.repository.LoanRepository;
import cit.backen.library.management.system.page.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanFacade {
    private final LoanMapper loanMapper;
    private final LoanRepository loanRepository;

    public LoanFacade(LoanMapper loanMapper, LoanRepository loanRepository) {
        this.loanMapper = loanMapper;
        this.loanRepository = loanRepository;
    }


    public ApiResponse<LoanResponse> addLoan(LoanRequest request){
        Loan loan = loanMapper.loanRequestToModel(request);
        long numberOfActiveLoans = loanRepository.countActiveLoansByMemberId(request.getMemberId(), Status.ACTIVE);

        if(numberOfActiveLoans>=3){
            throw new MemberHasThreeActiveLoans();
        }

        LoanResponse loanResponse = loanMapper.loanModelToResponse(loanRepository.save(loan));
        return new ApiResponse<>("SUCCESS","Loan Added Successfully",loanResponse);
    }


    public ApiResponse<PageResponse<LoanResponse>> getAllLoans(int page, int pageSize){
        int zeroBasedPage = Math.max(0,page-1);
        Pageable pageable = PageRequest.of(zeroBasedPage,pageSize, Sort.by("dateDue").ascending());
        Page<Loan> loansPage = loanRepository.findAll(pageable);

        List<LoanResponse> loanResponseList = loansPage.getContent()
                .stream()
                .map(loanMapper::loanModelToResponse)
                .toList();
        PageResponse<LoanResponse> pageResponse = new PageResponse<>(
                loanResponseList,
                page,
                pageSize,
                loansPage.getTotalElements(),
                loansPage.getTotalPages(),
                loansPage.isLast()
        );

        return new ApiResponse<>("SUCCESS","Page of Loans",pageResponse);
    }

}
