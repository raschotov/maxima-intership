package school.maxima.maximadms.mapper;

import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.DocumentTemplateDto;
import school.maxima.maximadms.dto.DocumentTemplateFieldDto;
import school.maxima.maximadms.models.DocumentTemplate;
import school.maxima.maximadms.models.DocumentTemplateField;
import school.maxima.maximadms.utils.MapperUtil;

@Component
public class DocumentTemplateMapper extends AbstractMapper<DocumentTemplate, DocumentTemplateDto> {

    @Override
    public DocumentTemplate toEntity(DocumentTemplateDto dto) {
        DocumentTemplate documentTemplate = mapper.map(dto, DocumentTemplate.class);
        documentTemplate.setFields(
            MapperUtil.mapList(dto.getFields(), DocumentTemplateField.class));
        return documentTemplate;
    }

    @Override
    public DocumentTemplateDto toDto(DocumentTemplate entity) {
        DocumentTemplateDto documentTemplateDto = mapper.map(entity, DocumentTemplateDto.class);
        documentTemplateDto.setFields(
            MapperUtil.mapList(entity.getFields(), DocumentTemplateFieldDto.class));
        return documentTemplateDto;
    }
}
