package school.maxima.maximadms.service;

import java.util.List;
import school.maxima.maximadms.dto.DocumentDto;

public interface DocumentService {

    List<DocumentDto> getAll();

    DocumentDto getById(Integer id);

    void saveOrUpdate(DocumentDto dto);

    void remove(Integer id);
}
