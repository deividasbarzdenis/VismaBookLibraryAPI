package lt.debarz.vismabooklibrary.controller;

import lombok.AllArgsConstructor;
import lt.debarz.vismabooklibrary.dto.BookDto;
import lt.debarz.vismabooklibrary.dto.BookUserDto;
import lt.debarz.vismabooklibrary.dto.ReservedDto;
import lt.debarz.vismabooklibrary.dto.UserDto;
import lt.debarz.vismabooklibrary.entity.Book;
import lt.debarz.vismabooklibrary.entity.Reserved;
import lt.debarz.vismabooklibrary.mapper.BookMapper;
import lt.debarz.vismabooklibrary.mapper.ReservedMapper;
import lt.debarz.vismabooklibrary.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@RequestMapping("/books")
@RestController
public class BookController {

    private final BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<Page<BookDto>> getAllBooks(@RequestParam("page") int page,
                                                     @RequestParam("size") int size,
                                                     @RequestParam("sort") String sort){
        return ResponseEntity.ok(
                bookService.findAllBooks(page, size, sort)
                .map(BookMapper.BOOK_MAPPER::mapDto));
    }

    @GetMapping("/by/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable long id){
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @GetMapping("/search/ByAuthor")
    public ResponseEntity<Page<BookDto>> getAllBooksByAuthor(@RequestParam("author") String author,
                                                             @RequestParam("page") int page,
                                                             @RequestParam("size") int size,
                                                             @RequestParam("sort") String sort){
        return ResponseEntity.ok(
                bookService.findAllBooksByAuthors(author, page, size, sort)
                        .map(BookMapper.BOOK_MAPPER::mapDto));
    }

    @GetMapping("/search/ByCategory")
    public ResponseEntity<Page<BookDto>> getAllBooksByCategories(@RequestParam("category") String category,
                                                             @RequestParam("page") int page,
                                                             @RequestParam("size") int size,
                                                             @RequestParam("sort") String sort){
        return ResponseEntity.ok(
                bookService.findAllByCategories(category, page, size, sort)
                        .map(BookMapper.BOOK_MAPPER::mapDto));
    }

    @GetMapping("/search/ByLanguage")
    public ResponseEntity<Page<BookDto>> getAllBooksByLanguage(@RequestParam("language") String language,
                                                                 @RequestParam("page") int page,
                                                                 @RequestParam("size") int size,
                                                                 @RequestParam("sort") String sort){
        return ResponseEntity.ok(
                bookService.findAllByLanguage(language, page, size, sort)
                        .map(BookMapper.BOOK_MAPPER::mapDto));
    }

    @GetMapping("/search/ByIsbnNumber")
    public ResponseEntity<Page<BookDto>> getAllBooksByIsbnNumber(@RequestParam("isbn10") String isbn10,
                                                               @RequestParam("isbn13") String isbn13,
                                                               @RequestParam("page") int page,
                                                               @RequestParam("size") int size,
                                                               @RequestParam("sort") String sort){
        return ResponseEntity.ok(
                bookService.findAllByIsbnNumber(isbn10, isbn13, page, size, sort)
                        .map(BookMapper.BOOK_MAPPER::mapDto));
    }

    @GetMapping("/search/ByName")
    public ResponseEntity<Page<BookDto>> getAllBooksByBookName(@RequestParam("name") String name,
                                                                 @RequestParam("page") int page,
                                                                 @RequestParam("size") int size,
                                                                 @RequestParam("sort") String sort){
        return ResponseEntity.ok(
                bookService.findAllByBookName(name, page, size, sort)
                        .map(BookMapper.BOOK_MAPPER::mapDto));
    }

    @GetMapping("/search/ByGuid")
    public ResponseEntity<BookDto> getBookByGuidNumber(@RequestParam("guid") String guid){
        return ResponseEntity.ok(BookMapper.BOOK_MAPPER.mapDto(
                bookService
                        .findBookByGuidNumber(guid)));
    }

    @PostMapping("/save")
    public ResponseEntity<BookDto> saveBook(@RequestBody @Valid BookDto bookDto){
        Book saveBook = bookService.saveBook(BookMapper.BOOK_MAPPER.mapEntity(bookDto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BookMapper.BOOK_MAPPER.mapDto(saveBook));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable long id){
        bookService.deleteBook(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody @Valid BookDto bookDto) {
        Book updatedBook = bookService.updateBook(BookMapper.BOOK_MAPPER.mapEntity(bookDto));
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(BookMapper.BOOK_MAPPER.mapDto(updatedBook));
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<BookDto> patchBook(@RequestBody @Valid BookDto bookDto) {
        Book patchedBook = bookService.patchBook(bookDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(BookMapper.BOOK_MAPPER.mapDto(patchedBook));
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservedDto> saveReservation(@RequestParam("days") int days,
                                                       @RequestParam("bookId") Long bookId,
                                                       @RequestParam("userId") Long userId){
        Reserved saveReservation = bookService.saveReservation(days,bookId,userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ReservedMapper.RESERVED_MAPPER.mapDto(saveReservation));
    }

    @GetMapping("/all/reservations")
    public ResponseEntity<Page<ReservedDto>> getAllBooksReservations(@RequestParam("page") int page,
                                                     @RequestParam("size") int size,
                                                     @RequestParam("sort") String sort){
        return ResponseEntity.ok(
                bookService.findAllBooksReservations(page, size, sort)
                        .map(reserved -> ReservedMapper.RESERVED_MAPPER.mapDto(reserved)));
    }
    @GetMapping("/all/reserved")
    public ResponseEntity<List<BookUserDto>> getAllReservedBooksWithUsers(){
        return ResponseEntity.ok(bookService.getAllReservedBooksWithUsers());
    }
    @DeleteMapping("/delete/reservation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable long id){
        bookService.deleteReservation(id);
    }

    @PatchMapping("/patch/reservation/{id}")
    public ResponseEntity<ReservedDto> patchBook(@RequestBody @Valid ReservedDto reservedDto) {
        Reserved patchedReservation = bookService.patchReservation(reservedDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(ReservedMapper.RESERVED_MAPPER.mapDto(patchedReservation));
    }
    @GetMapping("/by/user/reserved")
    public ResponseEntity<List<BookUserDto>> getBooksByUsers(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(bookService.getReservedBooksByUser(userId));
    }
}
