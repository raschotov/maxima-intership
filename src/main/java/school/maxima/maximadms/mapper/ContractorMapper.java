package school.maxima.maximadms.mapper;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.ContractorDto;
import school.maxima.maximadms.models.Contractor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContractorMapper implements Mapper<Contractor, ContractorDto> {

    private ModelMapper mapper;

    public ContractorMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Contractor toEntity(ContractorDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Contractor.class);
    }

    @Override
    public ContractorDto toDto(Contractor entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ContractorDto.class);
    }
}
