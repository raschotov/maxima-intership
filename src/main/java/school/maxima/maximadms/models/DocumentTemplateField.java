package school.maxima.maximadms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import school.maxima.maximadms.models.enums.FieldType;

/**
 * Сущность Поле шаблона документа
 */

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "document_fields")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE document_fields SET is_removed = true WHERE id=?")
@Where(clause = "is_removed=false")
public class DocumentTemplateField extends AbstractEntity {

    /**
     * Наименование поля
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * Тип поля
     */
    @Column(name = "field_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FieldType fieldType;
    /**
     * Плейсхолдер
     */
    @Column(name = "placeholder", nullable = false)
    private String placeholder;
    /**
     * Значение по умолчанию
     */
    @Column(name = "default_value")
    private String defaultValue;
}
