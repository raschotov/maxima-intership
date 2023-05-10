package school.maxima.maximadms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.maxima.maximadms.models.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Integer> {

}