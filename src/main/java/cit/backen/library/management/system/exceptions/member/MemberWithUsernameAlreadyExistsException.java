package cit.backen.library.management.system.exceptions.member;


public class MemberWithUsernameAlreadyExistsException extends RuntimeException {
    public MemberWithUsernameAlreadyExistsException(String message){
        super(
                "Member with username "+ message + "already exists"
        );
    }
}
