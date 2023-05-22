package school.maxima.maximadms.dto;

import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class FileDto extends AbstractDto{
    private Integer id;
    private byte[] incomingFile;
    private String fileName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
