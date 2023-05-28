package school.maxima.maximadms.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import school.maxima.maximadms.dto.DocumentDto;
import school.maxima.maximadms.dto.FileDto;
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
    public ResponseEntity<String> saveDocument(@RequestPart("file") MultipartFile multipartFile,
        @Valid @RequestPart("json") DocumentDto documentDto) {
        if (fnsService.getContractorInn(documentDto.getContractor())) {
            documentDto.setFile(makeFileDtoFromMultipartFile(multipartFile));
            documentService.saveOrUpdate(documentDto);
            return ResponseEntity.ok(
                "Информация об ИНН подтверждена. ФИО, день рождения и паспортные данные контрагента верные.");
        } else {
            return ResponseEntity.badRequest().body(
                "Информация об ИНН не найдена. Проверить правильность введённых данных и повторите попытку.");
        }
    }

    @PutMapping("/documents/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> updateDocument(@PathVariable("id") Integer id,
        @RequestBody DocumentDto documentDto) {
        if (!documentService.exists(id)) {
            return ResponseEntity.badRequest().body(
                "Документ с идентификатором " + id + " не найден");
        }
        documentService.saveOrUpdate(documentDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/documents/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {

        if (!documentService.exists(id)) {
            return ResponseEntity.badRequest().body(
                "Неверно заданный id для удаления");
        }

        documentService.remove(id);
        return ResponseEntity.ok().build();
    }

    private FileDto makeFileDtoFromMultipartFile(MultipartFile multipartFile) {
        try {
            return FileDto.builder()
                .incomingFile(multipartFile.getBytes())
                .fileName(multipartFile.getOriginalFilename())
                .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
