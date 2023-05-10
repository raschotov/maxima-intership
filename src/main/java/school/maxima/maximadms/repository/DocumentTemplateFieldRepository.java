package school.maxima.maximadms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.maxima.maximadms.models.DocumentTemplateField;

public interface DocumentTemplateFieldRepository extends
    JpaRepository<DocumentTemplateField, Integer> {

}