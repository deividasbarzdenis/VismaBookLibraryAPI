package lt.debarz.vismabooklibrary.mapper;

import lt.debarz.vismabooklibrary.dto.BookUserDto;
import lt.debarz.vismabooklibrary.entity.Book;
import lt.debarz.vismabooklibrary.entity.BookUser;
import lt.debarz.vismabooklibrary.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookUserMapper {

    BookUserMapper BOOK_USER_MAPPER = Mappers.getMapper(BookUserMapper.class);


    BookUserDto mapDto(BookUser bookUser);

    @Mapping(source = "book.name", target = "name")
    @Mapping(source = "book.language", target = "language")
    @Mapping(source = "book.publishedDate", target = "publishedDate")
    @Mapping(source = "book.isbn10", target = "isbn10")
    @Mapping(source = "book.isbn13", target = "isbn13")
    @Mapping(source = "book.guid", target = "guid")
    @Mapping(source = "book.reserved", target = "reserved")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.lastName", target = "lastName")
    BookUserDto mapBookUserDto(Book book, User user);

}
