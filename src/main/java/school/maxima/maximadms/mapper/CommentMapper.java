package school.maxima.maximadms.mapper;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.CommentDto;
import school.maxima.maximadms.models.Comment;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentMapper implements Mapper<Comment, CommentDto> {

    private ModelMapper mapper;

    public CommentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Comment toEntity(CommentDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Comment.class);
    }

    @Override
    public CommentDto toDto(Comment entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CommentDto.class);
    }
}
