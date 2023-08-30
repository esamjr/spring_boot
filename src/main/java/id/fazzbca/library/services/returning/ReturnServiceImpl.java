package id.fazzbca.library.services.returning;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.fazzbca.library.models.Book;
import id.fazzbca.library.models.Borrowing;
import id.fazzbca.library.models.Returning;
import id.fazzbca.library.payloads.req.ReturnRequest;
import id.fazzbca.library.repositories.BookRepository;
import id.fazzbca.library.repositories.BorrowingRepository;
import id.fazzbca.library.repositories.ReturningRepository;

@Service
public class ReturnServiceImpl implements ReturnService {
    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private ReturningRepository returningRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public ResponseEntity<?> returnBook(String bookId, ReturnRequest request) {
        Borrowing borrowing = borrowingRepository.findByBookIdAndUserIdAndReturnedAtIsNull(bookId, request.getUserId());

        if (borrowing == null) {
            return ResponseEntity.badRequest().body("No active borrowing found for this user and book");
        }
        Returning returning = new Returning();
        returning.setBorrowing(borrowing);
        returning.setReturnedAt(LocalDateTime.now());
        returningRepository.save(returning);

        // Update Borrowing entity
        borrowing.setReturnedAt(returning.getReturnedAt());
        borrowingRepository.save(borrowing);

        // Update book status
        Book book = borrowing.getBook();
        book.setBorrowed(false);
        bookRepository.save(book);

        return ResponseEntity.ok("Book returned successfully");
    }
}
