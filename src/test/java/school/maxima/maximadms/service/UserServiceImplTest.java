package school.maxima.maximadms.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import school.maxima.maximadms.dto.UserDto;
import school.maxima.maximadms.dto.UserReadDto;
import school.maxima.maximadms.mapper.UserMapper;
import school.maxima.maximadms.mapper.UserReadMapper;
import school.maxima.maximadms.models.User;
import school.maxima.maximadms.repository.UserRepository;
import school.maxima.maximadms.utils.MapperUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserServiceImplTest {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserReadMapper userReadMapper;
    private final UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, userReadMapper);

    User user1 = new User();
    User user2 = new User();
    User user3 = new User();
    Integer id;

    @BeforeEach
    private void generateTestData() {
        userRepository.saveAll(List.of(user1, user2));
        id = 1;
    }

    @Test
    void getAll() {
        List<UserReadDto> expected = MapperUtil.mapList(List.of(user1, user2), UserReadDto.class);
        List<UserReadDto> actual = userService.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void getById() {

    }

    @Test
    void existsByLogin() {
    }

    @Test
    void exists() {
    }

    @Test
    void saveOrUpdate() {
        userRepository.save(user3);
        List<UserDto> expected = MapperUtil.mapList(List.of(user1, user2, user3), UserDto.class);
        List<UserDto> actual = MapperUtil.mapList(userService.getAll(), UserDto.class);
        assertEquals(expected, actual);
    }

    @Test
    void remove() {
        userRepository.delete(user3);
        List<UserReadDto> expected = MapperUtil.mapList(List.of(user1, user2), UserReadDto.class);
        List<UserReadDto> actual = userService.getAll();
    }

    @Test
    void findByLogin() {

    }

    @AfterEach
    void deleteTestData() {
        userRepository.deleteAll();
        id = null;
    }
}