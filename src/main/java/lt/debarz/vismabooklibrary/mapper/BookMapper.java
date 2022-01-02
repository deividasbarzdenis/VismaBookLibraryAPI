package lt.debarz.vismabooklibrary.mapper;

import lt.debarz.vismabooklibrary.dto.BookDto;
import lt.debarz.vismabooklibrary.entity.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper BOOK_MAPPER  = Mappers.getMapper(BookMapper.class);

    BookDto mapDto(Book book);
    Book mapEntity(BookDto bookDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book updateBookFromDto(BookDto dto, @MappingTarget Book entity);


}
