package cit.backen.library.management.system.exceptions.book;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message){
        super(
                "Book with "+ message+ " Not Found"
        );
    }
}
