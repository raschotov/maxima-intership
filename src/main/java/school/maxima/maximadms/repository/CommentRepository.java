package school.maxima.maximadms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.maxima.maximadms.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}