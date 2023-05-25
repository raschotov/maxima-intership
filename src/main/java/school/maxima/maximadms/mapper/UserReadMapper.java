package school.maxima.maximadms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.UserReadDto;
import school.maxima.maximadms.models.User;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public User toEntity(UserReadDto dto) {
        return mapper.map(dto, User.class);
    }

    @Override
    public UserReadDto toDto(User entity) {
        return mapper.map(entity, UserReadDto.class);
    }
}
