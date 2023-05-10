package school.maxima.maximadms.mapper;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.DocumentTemplateDto;
import school.maxima.maximadms.models.DocumentTemplate;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentTemplateMapper implements Mapper<DocumentTemplate, DocumentTemplateDto> {

    private ModelMapper mapper;

    public DocumentTemplateMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public DocumentTemplate toEntity(DocumentTemplateDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, DocumentTemplate.class);
    }

    @Override
    public DocumentTemplateDto toDto(DocumentTemplate entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, DocumentTemplateDto.class);
    }
}
