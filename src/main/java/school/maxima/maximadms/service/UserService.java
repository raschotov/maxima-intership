package school.maxima.maximadms.service;

import java.util.List;
import school.maxima.maximadms.dto.UserDto;
import school.maxima.maximadms.dto.UserReadDto;

public interface UserService {

    List<UserReadDto> getAll();

    UserReadDto getById(Integer id);

    Boolean existsByLogin(String username);

    Boolean exists(Integer id);

    void saveOrUpdate(UserDto dto);

    void remove(Integer id);
}
