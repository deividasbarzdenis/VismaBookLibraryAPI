package lt.debarz.vismabooklibrary.mapper;


import lt.debarz.vismabooklibrary.dto.ReservedDto;
import lt.debarz.vismabooklibrary.entity.Book;
import lt.debarz.vismabooklibrary.entity.Reserved;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservedMapper {

    ReservedMapper RESERVED_MAPPER = Mappers.getMapper(ReservedMapper.class);

    ReservedDto mapDto(Reserved reserved);
    Reserved mapEntity(ReservedDto reservedDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Reserved updateReservationFromDto(ReservedDto dto, @MappingTarget Reserved entity);

}
