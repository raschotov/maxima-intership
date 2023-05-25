package school.maxima.maximadms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import school.maxima.maximadms.models.enums.UserRole;

/**
 * A DTO for the {@link school.maxima.maximadms.models.User} entity
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserReadDto extends AbstractDto {

    private String login;

    private UserRole userRole;

    private String firstName;

    private String lastName;

    private String surName;

    private String telegram;

    private String email;
}