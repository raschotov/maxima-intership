package school.maxima.maximadms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.DocumentTemplateFieldDto;
import school.maxima.maximadms.models.DocumentTemplateField;

@Component
public class DocumentTemplateFieldMapper implements
    Mapper<DocumentTemplateField, DocumentTemplateFieldDto> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public DocumentTemplateField toEntity(DocumentTemplateFieldDto dto) {
        return mapper.map(dto, DocumentTemplateField.class);
    }

    @Override
    public DocumentTemplateFieldDto toDto(DocumentTemplateField entity) {
        return mapper.map(entity, DocumentTemplateFieldDto.class);
    }
}
