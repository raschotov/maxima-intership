package school.maxima.maximadms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.UserDto;
import school.maxima.maximadms.models.User;

@Component
public class UserMapper implements Mapper<User, UserDto> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public User toEntity(UserDto dto) {
        return mapper.map(dto, User.class);
    }

    @Override
    public UserDto toDto(User entity) {
        return mapper.map(entity, UserDto.class);
    }
}
