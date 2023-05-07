package school.maxima.maximadms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        @RequestPart("files") MultipartFile[] multipartFile,
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse) throws IOException {
        if (fnsService.getContractorInn(documentDto.getContractor())) {
            documentDto.setFiles(makeListFileDtoFromMultipartFile(multipartFile));
            documentService.saveOrUpdate(documentDto);
            log.info("Успешное сохранение документа");
        } else {
            httpServletResponse.sendError();
        }
    }

    public List<FileDto> makeListFileDtoFromMultipartFile(MultipartFile[] multipartFile) {
        List<FileDto> fileDtoList = new ArrayList<>();
        try {
            for (MultipartFile file : multipartFile) {
                FileDto fileDto = FileDto.builder()
                    .incomingFile(file.getBytes())
                    .fileName(file.getName())
                    .build();
                fileDtoList.add(fileDto);
            }
            return fileDtoList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
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
}
