package lt.debarz.vismabooklibrary.repo;

import lt.debarz.vismabooklibrary.entity.Reserved;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface ReserveRepository extends JpaRepository<Reserved, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Reserved (due_date, reserve_date, reserve_days, book_id, user_id) " +
            "VALUES (:dueDate, :reserveDate, :reserveDays, :bookId, :userId);", nativeQuery = true)
    void saveReservation(@Param("dueDate") LocalDate dueDate,
                         @Param("reserveDate") LocalDate reserveDate,
                         @Param("reserveDays") int reserveDays,
                         @Param("bookId") Long bookId,
                         @Param("userId") Long userId);

    //@Query("SELECT COUNT(u) FROM Reserved u WHERE u.user.id = :userId")
    Integer countReservedByUserId(/*@Param("userId")*/ Long userId);



}
