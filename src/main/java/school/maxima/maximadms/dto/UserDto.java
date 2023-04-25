package school.maxima.maximadms.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
public class UserDto extends AbstractDto {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotNull
    private UserRole userRole;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String surName;

    @Pattern(regexp = ".*\\B@(?=\\w{5,32}\\b)[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*.*")
    private String telegram;

    @Email
    private String email;
}