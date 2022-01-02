package lt.debarz.vismabooklibrary.repo;

import lt.debarz.vismabooklibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u FROM User u LEFT JOIN Reserved r ON u.id = r.user.id")
    List<User> getAllUsersReservedBooks();

    @Query("select u from User u join Reserved r on r.user.id = :userId")
    List<User> findAllByReservedBooksUserId(@Param("userId") Long userId);
}
