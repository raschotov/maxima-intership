package school.maxima.maximadms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.maxima.maximadms.dto.CommentDto;
import school.maxima.maximadms.models.Comment;

@Component
public class CommentMapper implements Mapper<Comment, CommentDto> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Comment toEntity(CommentDto dto) {
        return mapper.map(dto, Comment.class);
    }

    @Override
    public CommentDto toDto(Comment entity) {
        return mapper.map(entity, CommentDto.class);
    }
}
