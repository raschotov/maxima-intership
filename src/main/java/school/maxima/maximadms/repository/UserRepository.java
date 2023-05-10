package school.maxima.maximadms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.maxima.maximadms.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}