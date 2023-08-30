package id.fazzbca.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.fazzbca.library.models.Borrowing;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    Borrowing findByBookIdAndUserIdAndReturnedAtIsNull(String bookId, String userId);
    List<Borrowing> findByReturnedAtIsNull();
}
