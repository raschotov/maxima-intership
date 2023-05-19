package school.maxima.maximadms.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.maxima.maximadms.dto.DocumentRequestDto;
import school.maxima.maximadms.dto.DocumentResponse;
import school.maxima.maximadms.mapper.DocumentMapper;
import school.maxima.maximadms.repository.DocumentRepository;
import school.maxima.maximadms.utils.MapperUtil;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    @Override
    public DocumentResponse getAll(int pageNo, int pageSize) {
        List<DocumentRequestDto> documents = MapperUtil.convertList(
                documentRepository.findAll(), documentMapper::toDto);

        //TODO: FIXME
        return new DocumentResponse(pageNo, pageSize);
    }

    @Override
    public DocumentRequestDto getById(Integer id) {
        return documentMapper.toDto(documentRepository.getReferenceById(id));
    }

    @Override
    public void saveOrUpdate(DocumentRequestDto documentRequestDto) {
        documentRepository.save(documentMapper.toEntity(documentRequestDto));
    }

    @Override
    public void remove(Integer id) {
        documentRepository.deleteById(id);
    }

    @Override
    public Boolean exists(Integer id) {
        return documentRepository.existsById(id);
    }
}
