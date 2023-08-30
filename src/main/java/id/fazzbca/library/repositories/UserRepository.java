package id.fazzbca.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.fazzbca.library.models.User;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.email = :identifier OR u.username = :identifier")
    User findByEmailOrUsername(@Param("identifier") String identifier);
}
