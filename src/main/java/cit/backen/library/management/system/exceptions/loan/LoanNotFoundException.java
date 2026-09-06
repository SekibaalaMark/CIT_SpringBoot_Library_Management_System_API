package cit.backen.library.management.system.exceptions.loan;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String message){
        super("Loan with "+message + " not found");
    }
}
