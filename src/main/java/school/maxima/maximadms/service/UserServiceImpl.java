package school.maxima.maximadms.service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.maxima.maximadms.dto.UserDto;
import school.maxima.maximadms.dto.UserReadDto;
import school.maxima.maximadms.mapper.UserMapper;
import school.maxima.maximadms.mapper.UserReadMapper;
import school.maxima.maximadms.models.User;
import school.maxima.maximadms.repository.UserRepository;
import school.maxima.maximadms.utils.MapperUtil;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final UserReadMapper mapperRead;

    @Override
    public List<UserReadDto> getAll() {
        return MapperUtil.mapList(userRepository.findAll(), UserReadDto.class);
    }

    @Override
    public UserReadDto getById(Integer id) {
        return mapperRead.toDto(userRepository.getReferenceById(id));
    }

    @Override
    public Boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    public Boolean exists(Integer id) {
        return userRepository.existsById(id);
    }

    @Override
    public void saveOrUpdate(UserDto dto) {
        mapper.toDto(userRepository.save(mapper.toEntity(dto)));
    }

    @Override
    public void remove(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
