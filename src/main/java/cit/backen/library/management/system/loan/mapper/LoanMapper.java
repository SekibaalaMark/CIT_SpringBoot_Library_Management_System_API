package cit.backen.library.management.system.loan.mapper;


import cit.backen.library.management.system.book.model.Book;
import cit.backen.library.management.system.book.repository.BookRepository;
import cit.backen.library.management.system.exceptions.book.BookNotFoundException;
import cit.backen.library.management.system.exceptions.member.MemberNotFoundException;
import cit.backen.library.management.system.loan.dto.LoanRequest;
import cit.backen.library.management.system.loan.dto.LoanResponse;
import cit.backen.library.management.system.loan.enums.Status;
import cit.backen.library.management.system.loan.model.Loan;
import cit.backen.library.management.system.member.model.Member;
import cit.backen.library.management.system.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoanMapper {
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public LoanMapper(MemberRepository memberRepository, BookRepository bookRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    public Loan loanRequestToModel(LoanRequest request){
        Loan loan = new Loan();
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(()-> new BookNotFoundException("id: "+ request.getBookId()));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(()-> new MemberNotFoundException("id: "+ request.getMemberId()));
        loan.setBook(book);
        loan.setMember(member);
        loan.setDateDue(LocalDate.now().plusDays(request.getPeriod()));
        loan.setStatus(Status.ACTIVE);
        return loan;
    }

    public LoanResponse loanModelToResponse(Loan loan){
        return new LoanResponse(
                loan.getDateDue(),
                loan.getMember().getId(),
                loan.getBook().getId(),
                loan.getStatus()
        );
    }
}

