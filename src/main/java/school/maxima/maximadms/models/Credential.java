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
import school.maxima.maximadms.models.enums.CredentialType;

/**
 * Сущность Учетные данные
 */

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "credentials")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE credentials SET is_removed = true WHERE id=?")
@Where(clause = "is_removed=false")
public class Credential extends AbstractEntity {

    /**
     * Паспортные данные контрагента (для физических лиц)
     */
    @Column(name = "passport")
    private String passport;
    /**
     * Инн контрагента
     */
    @Column(name = "inn")
    private String inn;
    /**
     * Другие характеристики контрагента
     */
    @Column(name = "text")
    private String text;
    /**
     * Версия учетных данных контрагента
     */
    @Column(name = "version")
    private Integer version;
    /**
     * Тип контрагента (физическое или юридическое лицо)
     */
    @Column(name = "credential_type")
    @Enumerated(EnumType.STRING)
    private CredentialType credentialType;
}
