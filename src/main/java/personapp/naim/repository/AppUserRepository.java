package personapp.naim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import personapp.naim.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String email);
}
