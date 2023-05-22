package school.maxima.maximadms.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import school.maxima.maximadms.dto.DocumentDto;
import school.maxima.maximadms.mapper.DocumentMapper;
import school.maxima.maximadms.models.Document;
import school.maxima.maximadms.repository.DocumentRepository;
import school.maxima.maximadms.utils.MapperUtil;

import javax.print.Doc;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class DocumentServiceImplTest {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final CurrentUserUtil currentUserUtil;

    private final DocumentServiceImpl documentService = new DocumentServiceImpl(documentRepository, documentMapper, currentUserUtil);

    Document document1 = new Document();
    Document document2 = new Document();
    Document document3 = new Document();
    Integer id;

    @BeforeEach
    private void generateTestData() {
        documentRepository.saveAll(List.of(document1, document2));
        id = 1;
    }

    @Test
    void getAll() {
        List<DocumentDto> expected = documentService.getAll();
        List<DocumentDto> actual = MapperUtil.mapList(List.of(document1, document2), DocumentDto.class);
        assertEquals(expected, actual);
    }

    @Test
    void exists() {
    }

    @Test
    void getById() {
        DocumentDto expected = documentService.getById(id);
        DocumentDto actual = documentMapper.toDto(documentRepository.getReferenceById(id));
        assertEquals(expected, actual);
    }

    @Test
    void saveOrUpdate() {
        documentRepository.save(document3);
        List<DocumentDto> expected = documentService.getAll();
        List<DocumentDto> actual = MapperUtil.mapList(List.of(document1, document2, document3), DocumentDto.class);
        assertEquals(expected, actual);
    }

    @Test
    void remove() {
        documentRepository.delete(document3);
        List<DocumentDto> expected = documentService.getAll();
        List<DocumentDto> actual = MapperUtil.mapList(List.of(document1, document2), DocumentDto.class);
        assertEquals(expected, actual);
    }

    @AfterEach
    void deleteTestData() {
        documentRepository.deleteAll();
        id = null;
    }
}