package school.maxima.maximadms.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Сущность Шаблон документа
 */

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "document_templates")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE document_templates SET is_removed = true WHERE id=?")
@Where(clause = "is_removed=false")
public class DocumentTemplate extends AbstractEntity {

    /**
     * Наименование шаблона документа
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * Версия шаблона документа
     */
    @Column(name = "version", nullable = false)
    private Integer version;
    /**
     * Список полей шаблона документа
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "document_templates_id")
    private List<DocumentTemplateField> fields;
}