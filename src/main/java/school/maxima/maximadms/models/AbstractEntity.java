package school.maxima.maximadms.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Абстрактная родительская сущность
 */

@MappedSuperclass
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity implements Serializable {

    /**
     * Реализация мягкого удаления сущности
     */
    @Column(name = "is_removed", nullable = false)
    boolean isRemoved = Boolean.FALSE;
    /**
     * Идентификатор сущности
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    /**
     * Время создания сущности
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    /**
     * Время модификации сущности
     */
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    /**
     * Время удаления сущности
     */
    @Column(name = "removed_at")
    private LocalDateTime removedAt;

    @PrePersist
    public void toCreate() {
        setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void toModified() {
        setModifiedAt(LocalDateTime.now());
    }

    @PreRemove
    public void toRemove() {
        setRemovedAt(LocalDateTime.now());
    }
}
