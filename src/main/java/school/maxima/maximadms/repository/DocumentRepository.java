package school.maxima.maximadms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.maxima.maximadms.models.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

}