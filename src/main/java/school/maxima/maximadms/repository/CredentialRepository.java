package school.maxima.maximadms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.maxima.maximadms.models.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {

}