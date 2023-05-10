package school.maxima.maximadms.mapper;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.UserReadDto;
import school.maxima.maximadms.models.User;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private ModelMapper mapper;

    public UserReadMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public User toEntity(UserReadDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, User.class);
    }

    @Override
    public UserReadDto toDto(User entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserReadDto.class);
    }
}
