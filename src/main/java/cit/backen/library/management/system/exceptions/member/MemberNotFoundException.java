package cit.backen.library.management.system.exceptions.member;



public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(String message){
        super(
                "Member with "+ message+ " Not Found"
        );
    }
}

