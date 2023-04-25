package school.maxima.maximadms.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import school.maxima.maximadms.models.DocumentTemplate;

/**
 * A DTO for the {@link DocumentTemplate} entity
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DocumentTemplateDto extends AbstractDto {

    @NotBlank
    private String name;

    @Positive
    private Integer version;

    @NotNull
    private List<DocumentTemplateFieldDto> fields;
}