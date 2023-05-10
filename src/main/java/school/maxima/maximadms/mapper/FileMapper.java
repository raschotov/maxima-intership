package school.maxima.maximadms.mapper;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.FileDto;
import school.maxima.maximadms.models.File;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileMapper implements Mapper<File, FileDto> {

    private ModelMapper mapper;

    public FileMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public File toEntity(FileDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, File.class);
    }

    @Override
    public FileDto toDto(File entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, FileDto.class);
    }
}
