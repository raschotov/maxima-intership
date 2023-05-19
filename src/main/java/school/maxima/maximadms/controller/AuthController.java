package school.maxima.maximadms.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import school.maxima.maximadms.dto.AuthResponseDTO;
import school.maxima.maximadms.dto.LoginDto;
import school.maxima.maximadms.dto.UserDto;
import school.maxima.maximadms.dto.UserReadDto;
import school.maxima.maximadms.models.User;
import school.maxima.maximadms.security.JwtGenerator;
import school.maxima.maximadms.service.UserService;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtGenerator jwtGenerator;

    @GetMapping("/users")
    public ResponseEntity<List<UserReadDto>> getDocument() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDto.getLogin(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto dto) {
        if (userService.existsByLogin(dto.getLogin())) {
            return ResponseEntity.badRequest().body("Такой логин уже занят!");
        }
        userService.saveOrUpdate(dto);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован!");
    }

    @PutMapping("/users/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id,
        @RequestBody UserDto dto) {
        if (!userService.exists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Пользователь с идентификатором " + id + " не найден");
        }
        userService.saveOrUpdate(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable("id") Integer id) {

        if (!userService.exists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Неверно заданный id для удаления");
        }

        userService.remove(id);
    }




}
