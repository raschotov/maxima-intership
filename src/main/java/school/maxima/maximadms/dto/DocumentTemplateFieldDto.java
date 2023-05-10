package school.maxima.maximadms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import school.maxima.maximadms.models.DocumentTemplateField;
import school.maxima.maximadms.models.enums.FieldType;

/**
 * A DTO for the {@link DocumentTemplateField} entity
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DocumentTemplateFieldDto extends AbstractDto {

    @NotBlank
    private String name;

    @NotNull
    private FieldType fieldType;

    @NotBlank
    private String placeholder;

    @NotBlank
    private String defaultValue;
}