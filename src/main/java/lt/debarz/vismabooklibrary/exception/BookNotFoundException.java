package lt.debarz.vismabooklibrary.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String guid){
        super("Book with guid number " + guid + " not found");
    }

    public BookNotFoundException(Long id){
        super("Book with id number " + id + " not found");
    }
}
