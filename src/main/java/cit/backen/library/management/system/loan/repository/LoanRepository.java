package cit.backen.library.management.system.loan.repository;


import cit.backen.library.management.system.loan.enums.Status;
import cit.backen.library.management.system.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {

    @Query("SELECT COUNT(l) FROM Loan l WHERE l.member.id = :memberId AND l.status = :status")
    long countActiveLoansByMemberId(@Param("memberId") Long memberId, @Param("status") Status status);


}
