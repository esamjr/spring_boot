package id.fazzbca.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.fazzbca.library.models.Book;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
  List<Book> findByIsDeleted(Boolean isDeleted);
  @Query(value = "SELECT * FROM books WHERE is_borrowed = true", nativeQuery = true)
  List<Book> findByIsBorrowed();
}
