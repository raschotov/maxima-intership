package school.maxima.maximadms.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import school.maxima.maximadms.dto.DocumentDto;
import school.maxima.maximadms.service.DocumentService;
import school.maxima.maximadms.service.FnsService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DocumentController {

    private final DocumentService documentService;
    private final FnsService fnsService;

    @GetMapping("/documents")
    public ResponseEntity<List<DocumentDto>> getDocument() {
        return new ResponseEntity<>(documentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/documents")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> saveDocument(@Valid @RequestBody DocumentDto documentDto/*,
        @RequestPart("files") MultipartFile[] multipartFile*/) {
        if (fnsService.getContractorInn(documentDto.getContractor())) {
            /*documentDto.setFiles(makeListFileDtoFromMultipartFile(multipartFile));*/
            documentService.saveOrUpdate(documentDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Информация об ИНН не найдена. Проверить правильность введённых данных и повторите попытку.");
        }
    }

    @PutMapping("/documents/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> updateDocument(@PathVariable("id") Integer id,
        @RequestBody DocumentDto documentDto) {
        if (!documentService.exists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Документ с идентификатором " + id + " не найден");
        }
        documentService.saveOrUpdate(documentDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/documents/{id}")
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable("id") Integer id) {

        if (!documentService.exists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Неверно заданный id для удаления");
        }

        documentService.remove(id);
    }

    /*private List<FileDto> makeListFileDtoFromMultipartFile(MultipartFile[] multipartFile) {
        List<FileDto> fileDtoList = new ArrayList<>();

        Arrays.stream(multipartFile).forEach(s -> {
            FileDto fileDto;
            try {
                fileDto = FileDto.builder()
                    .incomingFile(s.getBytes())
                    .fileName(s.getName())
                    .build();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fileDtoList.add(fileDto);
        });
        return fileDtoList;
    }*/
}
