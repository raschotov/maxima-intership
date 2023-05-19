package school.maxima.maximadms.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.maxima.maximadms.dto.DocumentDto;
import school.maxima.maximadms.mapper.DocumentMapper;
import school.maxima.maximadms.repository.DocumentRepository;
import school.maxima.maximadms.utils.MapperUtil;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;
    private final DocumentMapper mapper;

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
        mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public void remove(Integer id) {
        repository.deleteById(id);
    }
}
