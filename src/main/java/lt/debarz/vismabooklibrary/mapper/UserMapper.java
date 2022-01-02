package lt.debarz.vismabooklibrary.mapper;

import lt.debarz.vismabooklibrary.dto.UserDto;
import lt.debarz.vismabooklibrary.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto mapDto(User user);
    User mapEntity(UserDto userDto);
}
