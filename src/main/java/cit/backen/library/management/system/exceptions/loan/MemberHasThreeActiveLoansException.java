package cit.backen.library.management.system.exceptions.loan;

public class MemberHasThreeActiveLoansException extends RuntimeException {
    public MemberHasThreeActiveLoansException(){
        super("Member Has 3 active loans running");
    }
}
