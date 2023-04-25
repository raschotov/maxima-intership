package school.maxima.maximadms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.maxima.maximadms.models.File;

public interface FileRepository extends JpaRepository<File, Integer> {

}