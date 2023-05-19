package school.maxima.maximadms.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.maxima.maximadms.controller.AuthController;
import school.maxima.maximadms.dto.DocumentDto;
import school.maxima.maximadms.mapper.DocumentMapper;
import school.maxima.maximadms.models.Document;
import school.maxima.maximadms.models.User;
import school.maxima.maximadms.repository.DocumentRepository;
import school.maxima.maximadms.repository.UserRepository;
import school.maxima.maximadms.utils.MapperUtil;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;
    private final DocumentMapper mapper;

    private final UserRepository userRepository;


    private final AuthController authController;

    @Override
    public List<DocumentDto> getAll() {
        return MapperUtil.mapList(repository.findAll(), DocumentDto.class);
    }

    @Override
    public Boolean exists(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public DocumentDto getById(Integer id) {
        return mapper.toDto(repository.getReferenceById(id));
    }

    @Override
    public void saveOrUpdate(DocumentDto dto) {
        Document document = mapper.toEntity(dto);
        LocalDateTime currentTime = LocalDateTime.now();
        document.setModifiedAt(currentTime);
        document.setModifiedAtUser(getCurrentUser());

        if (dto.getId() == null) {
            document.setCreatedAt(currentTime);
            document.setCreatedAtUser(getCurrentUser());
        }

        repository.save(document);
    }



    @Override
    public void remove(Integer id) {
        Document document = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Документ с : " + id + " не найден"));
        document.setModifiedAt(LocalDateTime.now());
        document.setModifiedAtUser(getCurrentUser());
        document.setRemovedAt(LocalDateTime.now());
        document.setRemovedAtUser(getCurrentUser());
        repository.save(document);
    }


    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Пользователь не аутентифицирован");
        }
        String login = authentication.getName();
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
    }
}
