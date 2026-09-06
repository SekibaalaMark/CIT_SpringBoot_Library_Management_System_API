package cit.backen.library.management.system.exceptions.member;


public class MemberWithNinNumberAlreadyExistException  extends RuntimeException {
    public MemberWithNinNumberAlreadyExistException (String message){
        super(
                "Member with NIN number: "+ message + " already exists"
        );
    }
}