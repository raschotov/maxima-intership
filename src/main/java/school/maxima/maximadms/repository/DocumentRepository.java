package school.maxima.maximadms.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import school.maxima.maximadms.models.Document;
import school.maxima.maximadms.models.DocumentTemplate;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

    Optional<Document> findByTemplate(DocumentTemplate template);

    Optional<Document> findByInternalRegistryNumber(String number);

}