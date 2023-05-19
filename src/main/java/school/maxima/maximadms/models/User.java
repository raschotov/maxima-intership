package school.maxima.maximadms.models;

import java.time.LocalDateTime;
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
import school.maxima.maximadms.models.enums.UserRole;

/**
 * Сущность Пользователь
 */

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE users SET is_removed = true WHERE id=?")
@Where(clause = "is_removed=false")
public class User extends AbstractEntity {

    /**
     * логин
     */
    @Column(name = "login", nullable = false)
    private String login;
    /**
     * пароль
     */
    @Column(name = "password", nullable = false)
    private String password;
    /**
     * Роли пользователя
     */
    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
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
     * Фамилия
     */
    @Column(name = "sur_name", nullable = false)
    private String surName;
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
     * Дата последнего визита
     */
    @Column(name = "last_visit", nullable = false)
    private LocalDateTime lastVisit;



}
