package school.maxima.maximadms.mapper;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.DocumentTemplateFieldDto;
import school.maxima.maximadms.models.DocumentTemplateField;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentTemplateFieldMapper implements
    Mapper<DocumentTemplateField, DocumentTemplateFieldDto> {

    private ModelMapper mapper;

    public DocumentTemplateFieldMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public DocumentTemplateField toEntity(DocumentTemplateFieldDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, DocumentTemplateField.class);
    }

    @Override
    public DocumentTemplateFieldDto toDto(DocumentTemplateField entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, DocumentTemplateFieldDto.class);
    }
}
