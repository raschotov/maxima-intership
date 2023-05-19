package school.maxima.maximadms.mapper;

import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.ContractorDto;
import school.maxima.maximadms.dto.DocumentDto;
import school.maxima.maximadms.dto.DocumentTemplateDto;
import school.maxima.maximadms.dto.FileDto;
import school.maxima.maximadms.models.Contractor;
import school.maxima.maximadms.models.Document;
import school.maxima.maximadms.models.DocumentTemplate;
import school.maxima.maximadms.models.File;
import school.maxima.maximadms.utils.MapperUtil;

@Component
public class DocumentMapper extends AbstractMapper<Document, DocumentDto> {

    @Override
    public Document toEntity(DocumentDto dto) {
        Document document = mapper.map(dto, Document.class);
        document.setTemplate(mapper.map(dto.getTemplate(), DocumentTemplate.class));
        document.setContractor(mapper.map(dto.getContractor(), Contractor.class));
        document.setFiles(MapperUtil.mapList(dto.getFiles(), File.class));
        return document;
    }

    @Override
    public DocumentDto toDto(Document entity) {
        DocumentDto documentDto = mapper.map(entity, DocumentDto.class);
        documentDto.setTemplate(mapper.map(entity.getTemplate(), DocumentTemplateDto.class));
        documentDto.setContractor(mapper.map(entity.getContractor(), ContractorDto.class));
        documentDto.setFiles(MapperUtil.mapList(entity.getFiles(), FileDto.class));
        return documentDto;
    }
}