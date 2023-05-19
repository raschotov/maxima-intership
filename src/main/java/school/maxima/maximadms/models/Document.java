package school.maxima.maximadms.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Сущность Документ
 */

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "documents")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE documents SET is_removed = true WHERE id=?")
@Where(clause = "is_removed=false")
public class Document extends AbstractEntity {

    /**
     * Наменование документа
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * Внешний регистрационный номер документа
     */
    @Column(name = "internal_registry_number", nullable = false)
    private String internalRegistryNumber;
    /**
     * Шаблон документа
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "template_id")
    private DocumentTemplate template;
    /**
     * Контрагент
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;
    /**
     * Прикрепленные файлы
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private List<File> files;
}
