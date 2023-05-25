package school.maxima.maximadms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.FileDto;
import school.maxima.maximadms.models.File;

@Component
public class FileMapper implements Mapper<File, FileDto> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public File toEntity(FileDto dto) {
        return mapper.map(dto, File.class);
    }

    @Override
    public FileDto toDto(File entity) {
        return mapper.map(entity, FileDto.class);
    }
}
