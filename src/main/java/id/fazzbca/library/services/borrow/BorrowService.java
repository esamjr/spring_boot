package id.fazzbca.library.services.borrow;

import org.springframework.http.ResponseEntity;
import id.fazzbca.library.payloads.req.BorrowRequest;

public interface BorrowService {
    ResponseEntity<?> borrowBook(String bookId, BorrowRequest request);
    ResponseEntity<?> getBorrowedBooksInfo();
    
}
