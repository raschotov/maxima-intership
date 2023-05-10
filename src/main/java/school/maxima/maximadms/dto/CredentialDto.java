package school.maxima.maximadms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A DTO for the {@link school.maxima.maximadms.models.Credential} entity
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CredentialDto extends AbstractDto {

    @NotBlank
    private String text;

    @Positive
    private Integer version;

    @NotBlank
    private String credentialType;

    @NotBlank
    private String passport;

    @NotBlank
    private String inn;
}