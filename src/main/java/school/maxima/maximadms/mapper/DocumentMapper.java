package school.maxima.maximadms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.ContractorDto;
import school.maxima.maximadms.dto.DocumentDto;
import school.maxima.maximadms.dto.DocumentTemplateDto;
import school.maxima.maximadms.dto.FileDto;
import school.maxima.maximadms.models.Contractor;
import school.maxima.maximadms.models.Document;
import school.maxima.maximadms.models.DocumentTemplate;
import school.maxima.maximadms.models.File;

@Component
public class DocumentMapper implements Mapper<Document, DocumentDto> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Document toEntity(DocumentDto dto) {
        Document document = mapper.map(dto, Document.class);
        document.setTemplate(mapper.map(dto.getTemplate(), DocumentTemplate.class));
        document.setContractor(mapper.map(dto.getContractor(), Contractor.class));
        document.setFile(mapper.map(dto.getFile(), File.class));
        return document;
    }

    @Override
    public DocumentDto toDto(Document entity) {
        DocumentDto documentDto = mapper.map(entity, DocumentDto.class);
        documentDto.setTemplate(mapper.map(entity.getTemplate(), DocumentTemplateDto.class));
        documentDto.setContractor(mapper.map(entity.getContractor(), ContractorDto.class));
        documentDto.setFile(mapper.map(entity.getFile(), FileDto.class));
        return documentDto;
    }
}