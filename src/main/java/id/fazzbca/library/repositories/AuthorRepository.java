package id.fazzbca.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.fazzbca.library.models.Author;

public interface AuthorRepository extends JpaRepository<Author, String> {
  // sql: mencari author dari nama author: select * from authors where name like
  // '%a%'
  List<Author> findByNameContaining(String name);

  // where name = value
  Author findByName(String name);

  @Query(value = "select * from authors where name like %?%", nativeQuery = true)
  List<Author> getAuthorByName(String name);
}
