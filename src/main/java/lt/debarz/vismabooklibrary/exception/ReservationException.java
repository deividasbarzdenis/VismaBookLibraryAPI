package lt.debarz.vismabooklibrary.exception;

public class ReservationException extends RuntimeException{

    public ReservationException(Long userId){
        super("User with id number " + userId + " exceed book reserved books limit");
    }
    public ReservationException(long reservationId){
        super("Reservation with id number " + reservationId + " not found");
    }
}
