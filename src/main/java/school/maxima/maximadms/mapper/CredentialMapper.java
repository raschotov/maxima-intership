package school.maxima.maximadms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.CredentialDto;
import school.maxima.maximadms.models.Credential;

@Component
public class CredentialMapper implements Mapper<Credential, CredentialDto> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Credential toEntity(CredentialDto dto) {
        return mapper.map(dto, Credential.class);
    }

    @Override
    public CredentialDto toDto(Credential entity) {
        return mapper.map(entity, CredentialDto.class);
    }
}
