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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Сущность Контрагент
 */

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "contractors")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE contractors SET is_removed = true WHERE id=?")
@Where(clause = "is_removed=false")
public class Contractor extends AbstractEntity {

    /**
     * Фамилия
     */
    @Column(name = "sur_name", nullable = false)
    private String surName;
    /**
     * Имя
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;
    /**
     * Отчество
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;
    /**
     * День рождения
     */
    @Column(name = "b_date", nullable = false)
    private String date;
    /**
     * Телефон
     */
    @Column(name = "phone", nullable = false)
    private String phone;
    /**
     * Аккаунт Телеграм
     */
    @Column(name = "telegram")
    private String telegram;
    /**
     * Электронная почта
     */
    @Column(name = "email", nullable = false)
    private String email;
    /**
     * Учетные данные
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Credential credential;
    /**
     * Страна
     */
    @Column(name = "country", nullable = false)
    private String country;
    /**
     * Комментарии
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contractor_id")
    private List<Comment> comments;
}
