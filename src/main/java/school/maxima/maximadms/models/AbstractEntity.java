package school.maxima.maximadms.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
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
     * Пользователь создавший сущность
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_created_at")
    private User createdAtUser;
    /**
     * Время модификации сущности
     */
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    /**
     * Пользователь изменивший сущность
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_modified_at")
    private User modifiedAtUser;
    /**
     * Время удаления сущности
     */
    @Column(name = "removed_at")
    private LocalDateTime removedAt;
    /**
     * Пользователь удаливший сущность
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_removed_at")
    private User removedAtUser;

    /*@PrePersist
    public void toCreate() {
        setCreatedAt(LocalDateTime.now());
        setCreatedAtUser(getCurrentUser());
    }

    @PreUpdate
    public void toModified() {
        setModifiedAt(LocalDateTime.now());
        setModifiedAtUser(getCurrentUser());
    }

    @PreRemove
    public void toRemove() {
        setRemovedAt(LocalDateTime.now());
        setRemovedAtUser(getCurrentUser());
    }*/
}
