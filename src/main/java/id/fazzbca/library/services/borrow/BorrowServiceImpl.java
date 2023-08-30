package id.fazzbca.library.services.borrow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.fazzbca.library.models.Book;
import id.fazzbca.library.models.Borrowing;
import id.fazzbca.library.models.User;
import id.fazzbca.library.payloads.req.BorrowRequest;
import id.fazzbca.library.payloads.res.ResponseHandler;
import id.fazzbca.library.repositories.BookRepository;
import id.fazzbca.library.repositories.BorrowingRepository;
import id.fazzbca.library.repositories.UserRepository;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public ResponseEntity<?> borrowBook(String bookId, BorrowRequest request) {
        // Validate user existence
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        // Validate book existence and availability
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null || book.isBorrowed()) {
            return ResponseEntity.badRequest().body("Book not available for borrowing");
        }

        // Create and save Borrowing entity
        Borrowing borrowing = new Borrowing();
        borrowing.setUser(user);
        borrowing.setBook(book);
        borrowing.setBorrowedAt(LocalDateTime.now());
        borrowingRepository.save(borrowing);

        // Update book status
        book.setBorrowed(true);
        bookRepository.save(book);

        return ResponseEntity.ok("Book borrowed successfully");
    }
    @Override
    public ResponseEntity<?> getBorrowedBooksInfo() {
        List<Borrowing> borrowings = borrowingRepository.findByReturnedAtIsNull();

        List<Map<String, Object>> bookInfoList = new ArrayList<>();

        for (Borrowing borrowing : borrowings) {
            Book book = borrowing.getBook();
            User user = borrowing.getUser();

            Map<String, Object> bookInfo = new HashMap<>();
            bookInfo.put("id", book.getId());
            bookInfo.put("title", book.getTitle());
            bookInfo.put("year", book.getYear());
            bookInfo.put("author", book.getAuthor());
            bookInfo.put("borrowed", book.isBorrowed());
            bookInfo.put("borrowed_by", user.getEmail());
            bookInfo.put("publisher", book.getPublisher());
            bookInfo.put("createdAt", book.getCreatedAt());
            bookInfo.put("updatedAt", book.getUpdatedAt());

            bookInfoList.add(bookInfo);
        }

        return ResponseHandler.responseData(200, "success", bookInfoList);
    }
}
