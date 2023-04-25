package school.maxima.maximadms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Сущность Комментарий
 */

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "comments")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE comments SET is_removed = true WHERE id=?")
@Where(clause = "is_removed=false")
public class Comment extends AbstractEntity {

    /**
     * Содержание комментария
     */
    @Column(name = "text", nullable = false)
    private String text;
}
