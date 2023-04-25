package school.maxima.maximadms.mapper;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.CredentialDto;
import school.maxima.maximadms.models.Credential;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CredentialMapper implements Mapper<Credential, CredentialDto> {

    private ModelMapper mapper;

    public CredentialMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Credential toEntity(CredentialDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Credential.class);
    }

    @Override
    public CredentialDto toDto(Credential entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CredentialDto.class);
    }
}
