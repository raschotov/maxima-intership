package school.maxima.maximadms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import school.maxima.maximadms.service.FnsService;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DocumentController {

    private final DocumentService documentService;
    private final FnsService fnsService;

    @GetMapping("/getDocument")
    public List<DocumentDto> getDocument() {
        log.info("Получаем данные для сервиса клиента");
        return documentService.getAll();
    }

    @PostMapping("/saveDocument")
    public void saveDocument(@Valid @RequestBody DocumentDto documentDto,
        @RequestPart("files") MultipartFile[] multipartFile) throws IOException {
        if (fnsService.getContractorInnElseThrow(documentDto.getContractor())) {
            documentDto.setFiles(makeListFileDtoFromMultipartFile(multipartFile));
            documentService.saveOrUpdate(documentDto);
            log.info("Успешное сохранение документа");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Информация об ИНН не найдена. Проверить правильность введённых данных и повторите попытку.");
        }
    }

    @PutMapping("/updateDocument")
    public void updateDocument(@RequestBody DocumentDto documentDto) {
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

    private List<FileDto> makeListFileDtoFromMultipartFile(MultipartFile[] multipartFile) {
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
    }
}
