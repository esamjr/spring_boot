package id.fazzbca.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.fazzbca.library.models.Book;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
  List<Book> findByIsDeleted(Boolean isDeleted);
}
