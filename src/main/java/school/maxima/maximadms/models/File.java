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
 * Сущность Файл
 */

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "files")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE files SET is_removed = true WHERE id=?")
@Where(clause = "is_removed=false")
public class File extends AbstractEntity {

    /**
     * Содержимое файла в виде массива байт
     */
    @Column(name = "incoming_file", nullable = false)
    private byte[] incomingFile;
    /**
     * Имя файла
     */
    @Column(name = "file_name", nullable = false)
    private String fileName;
}
