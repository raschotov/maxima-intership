package school.maxima.maximadms.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import school.maxima.maximadms.dto.DocumentDto;
import school.maxima.maximadms.dto.FileDto;
import school.maxima.maximadms.service.DocumentService;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/getDocument")
    public List<DocumentDto> getDocument() {
        log.info("Получаем данные для сервиса клиента");
        return documentService.getAll();
    }

    @PostMapping("/saveDocument")
    public void saveEmployee(@Valid @RequestBody DocumentDto documentDto,
        @RequestPart("files") MultipartFile[] multipartFile) {
        List<FileDto> fileDtoList = new ArrayList<>();
        try {
            for (MultipartFile file : multipartFile) {
                FileDto fileDto = FileDto.builder()
                    .incomingFile(file.getBytes())
                    .fileName(file.getName())
                    .build();
                fileDtoList.add(fileDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        documentDto.setFiles(fileDtoList);
        documentService.saveOrUpdate(documentDto);
        log.info("Успешное сохранение сотрудника");
    }

    @PutMapping("/updateDocument")
    public void updateEmployee(@RequestBody DocumentDto documentDto) {
        documentService.saveOrUpdate(documentDto);
    }

    @DeleteMapping("/deleteDocument/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {

        if (id == -1L) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Неверно заданный id для удаления");
        }

        documentService.remove(id);
    }
}
