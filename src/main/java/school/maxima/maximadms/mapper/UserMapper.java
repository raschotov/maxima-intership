package school.maxima.maximadms.mapper;

import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.UserDto;
import school.maxima.maximadms.models.User;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(UserDto.class, User.class)
            .addMappings(m -> m.skip(User::setPassword)).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(UserDto source, User destination) {
        destination.setPassword(passwordEncoder.encode(source.getPassword()));
    }
}
