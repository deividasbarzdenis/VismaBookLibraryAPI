package lt.debarz.vismabooklibrary.service;

import lombok.AllArgsConstructor;
import lt.debarz.vismabooklibrary.dto.BookDto;
import lt.debarz.vismabooklibrary.dto.BookUserDto;
import lt.debarz.vismabooklibrary.dto.ReservedDto;
import lt.debarz.vismabooklibrary.dto.UserDto;
import lt.debarz.vismabooklibrary.entity.Book;
import lt.debarz.vismabooklibrary.entity.BookUser;
import lt.debarz.vismabooklibrary.entity.Reserved;
import lt.debarz.vismabooklibrary.entity.User;
import lt.debarz.vismabooklibrary.exception.BookNotFoundException;
import lt.debarz.vismabooklibrary.exception.ReservationException;
import lt.debarz.vismabooklibrary.exception.UserNotFoundException;
import lt.debarz.vismabooklibrary.mapper.BookMapper;
import lt.debarz.vismabooklibrary.mapper.BookUserMapper;
import lt.debarz.vismabooklibrary.mapper.ReservedMapper;
import lt.debarz.vismabooklibrary.repo.BookRepository;
import lt.debarz.vismabooklibrary.repo.ReserveRepository;
import lt.debarz.vismabooklibrary.repo.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.util.StreamUtils.zip;


@AllArgsConstructor
@Service
public class BookService {


    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ReserveRepository reserveRepository;

    public Page<Book> findAllBooks(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return bookRepository.findAll(pageable);
    }

    public BookDto findBookById(long id){
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }
        Book book = getById(id);
        return BookMapper.BOOK_MAPPER.mapDto(book);
    }
    public Page<Book> findAllBooksByAuthors(String author, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return bookRepository.findByAuthorsName(author, pageable);
    }

    public Page<Book> findAllByCategories(String category, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return bookRepository.findAllByCategoriesName(category, pageable);
    }

    public Page<Book> findAllByLanguage(String language, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return bookRepository.findBookByLanguage(language, pageable);
    }

    public Page<Book> findAllByIsbnNumber(String isbn10, String isbn13, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return bookRepository.findBookByIsbn10OrIsbn13(isbn10, isbn13, pageable);
    }

    public Page<Book> findAllByBookName(String name, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return bookRepository.findAllByName(name, pageable);
    }

    public Book findBookByGuidNumber(String guid) {
        return bookRepository
                .findByGuid(guid)
                .orElseThrow(() -> new BookNotFoundException(guid));
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }

    public Book updateBook(Book book) {
        if (book.getId() == null) {
            throw new BookNotFoundException(book.getId());
        }
        bookRepository.save(book);
        return book;
    }

    public Book patchBook(BookDto bookDto) {
        Long id = bookDto.getId();
        if (id == null) {
            throw new BookNotFoundException(id);
        }
        Book book = getById(id);
        Book patchedBook = BookMapper.BOOK_MAPPER.updateBookFromDto(bookDto, book);
        bookRepository.save(patchedBook);
        return book;
    }

    public Reserved saveReservation(int days, Long bookId, Long userId){
        Integer limit = reserveRepository.countReservedByUserId(userId);
        if( limit >= 3){
            throw new ReservationException(userId);
        }
        Reserved reserved = new Reserved();
        reserved.setReserveDays(days);
        LocalDate reserveDate = reserved.getReserveDate();
        reserved.setDueDate(reserveDate);
        LocalDate dueDate = reserved.getDueDate();
        int reserveDays = reserved.getReserveDays();
        reserveRepository.saveReservation(dueDate, reserveDate, reserveDays, bookId, userId);
        return reserved;
    }

    public Page<Reserved> findAllBooksReservations(int page, int size, String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return reserveRepository.findAll(pageable);
    }

    public List<BookUserDto> getAllReservedBooksWithUsers(){
        List<Book> books = bookRepository.getAllReservedBooksWithUsers();
        List<User> users = userRepository.getAllUsersReservedBooks();
        return zip(
                books.stream(),
                users.stream(),
                BookUserMapper.BOOK_USER_MAPPER::mapBookUserDto)
                .collect(Collectors.toList());
    }

    public void deleteReservation(long id){
        if(!reserveRepository.existsById(id)){
            throw new ReservationException(id);
        }
        reserveRepository.deleteById(id);
    }

    public Reserved patchReservation(ReservedDto reservedDto) {
        Long id = reservedDto.getId();
        if (id == null) {
            throw new ReservationException(id);
        }
        Reserved reserved = getReservationById(id);
        Reserved patchedReservation = ReservedMapper.RESERVED_MAPPER.updateReservationFromDto(reservedDto, reserved);
        reserveRepository.save(patchedReservation);
        return reserved;
    }

    public List<BookUserDto> getReservedBooksByUser(Long userId){
        if (userId == null) {
            throw new UserNotFoundException(userId);
        }
        List<User> users = userRepository.findAllByReservedBooksUserId(userId);
        List<Book> books = bookRepository.getAllByReservedUserId(userId);
        return zip(
                books.stream(),
                users.stream(),
                BookUserMapper.BOOK_USER_MAPPER::mapBookUserDto)
                .collect(Collectors.toList());
    }

    private Book getById(long id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    private Reserved getReservationById(long id) {
        return reserveRepository
                .findById(id)
                .orElseThrow(() -> new ReservationException(id));
    }
}
