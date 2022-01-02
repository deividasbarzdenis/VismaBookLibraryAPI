package lt.debarz.vismabooklibrary.repo;

import lt.debarz.vismabooklibrary.entity.Book;
import lt.debarz.vismabooklibrary.entity.BookUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {


    Page<Book> findByAuthorsName(String author, Pageable pageable);

    Page<Book> findAllByCategoriesName(String category, Pageable pageable);

    Page<Book> findBookByLanguage(String language, Pageable pageable);

    Page<Book> findBookByIsbn10OrIsbn13(String isbn10, String isbn13, Pageable pageable);

    Page<Book> findAllByName(String name, Pageable pageable);

    Optional<Book> findByGuid(String guid);

    @Query("SELECT b, r FROM Book b " +
            "JOIN Reserved r ON b.id = r.book.id ")
    List<Book> getAllReservedBooksWithUsers();

    List<Book> getAllByReservedUserId(@Param("userId") Long userId);
}
