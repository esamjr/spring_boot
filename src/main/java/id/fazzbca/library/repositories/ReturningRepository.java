package id.fazzbca.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.fazzbca.library.models.Returning;

public interface ReturningRepository extends JpaRepository<Returning, String> {
  
}
