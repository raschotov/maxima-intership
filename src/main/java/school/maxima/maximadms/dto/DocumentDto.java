package school.maxima.maximadms.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A DTO for the {@link school.maxima.maximadms.models.Document} entity
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DocumentDto extends AbstractDto {

    @NotBlank
    private String internalRegistryNumber;

    @NotNull
    private DocumentTemplateDto template;

    @NotNull
    private ContractorDto contractor;

    @NotNull
    private List<FileDto> files;
}
