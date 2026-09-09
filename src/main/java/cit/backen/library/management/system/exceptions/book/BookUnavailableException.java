package cit.backen.library.management.system.exceptions.book;

public class BookUnavailableException extends RuntimeException {
    public BookUnavailableException(Long message){
        super("Book is currently unavailable id: " + message);
    }

}
