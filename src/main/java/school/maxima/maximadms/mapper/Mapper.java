package school.maxima.maximadms.mapper;

import school.maxima.maximadms.dto.AbstractDto;
import school.maxima.maximadms.models.AbstractEntity;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);

    D toDto(E entity);
}
