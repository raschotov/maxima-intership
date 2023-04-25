package school.maxima.maximadms.mapper;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.DocumentDto;
import school.maxima.maximadms.models.Document;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentMapper implements Mapper<Document, DocumentDto> {

    private ModelMapper mapper;

    public DocumentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Document toEntity(DocumentDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Document.class);
    }

    @Override
    public DocumentDto toDto(Document entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, DocumentDto.class);
    }
}
