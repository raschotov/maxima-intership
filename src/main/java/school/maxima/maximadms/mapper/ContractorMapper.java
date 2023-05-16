package school.maxima.maximadms.mapper;

import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.CommentDto;
import school.maxima.maximadms.dto.ContractorDto;
import school.maxima.maximadms.dto.CredentialDto;
import school.maxima.maximadms.models.Comment;
import school.maxima.maximadms.models.Contractor;
import school.maxima.maximadms.models.Credential;
import school.maxima.maximadms.utils.MapperUtil;

@Component
public class ContractorMapper extends AbstractMapper<Contractor, ContractorDto> {

    @Override
    public Contractor toEntity(ContractorDto dto) {
        Contractor contractor = mapper.map(dto, Contractor.class);
        contractor.setCredential(mapper.map(dto.getCredential(), Credential.class));
        contractor.setComments(MapperUtil.mapList(dto.getComments(), Comment.class));
        return contractor;
    }

    @Override
    public ContractorDto toDto(Contractor entity) {
        ContractorDto contractorDto = mapper.map(entity, ContractorDto.class);
        contractorDto.setCredential(mapper.map(entity.getCredential(), CredentialDto.class));
        contractorDto.setComments(MapperUtil.mapList(entity.getComments(), CommentDto.class));
        return contractorDto;
    }
}
