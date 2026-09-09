package cit.backen.library.management.system.exceptions.book;


public class BookWithIsbnAlreadyExistsException extends RuntimeException {
    public BookWithIsbnAlreadyExistsException(String message){
        super("Book with ISBN: "+ message + " already exists!");
    }
}
