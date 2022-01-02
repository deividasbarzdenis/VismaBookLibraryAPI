package lt.debarz.vismabooklibrary.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long userId){
        super("User with id number " + userId + " not found");
    }
}
