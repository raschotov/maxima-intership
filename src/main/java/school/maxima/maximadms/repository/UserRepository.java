package school.maxima.maximadms.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import school.maxima.maximadms.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByLogin(String username);

    Boolean existsByLogin(String username);
}