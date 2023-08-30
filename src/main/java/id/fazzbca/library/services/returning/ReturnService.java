package id.fazzbca.library.services.returning;
import org.springframework.http.ResponseEntity;

import id.fazzbca.library.payloads.req.ReturnRequest;

public interface ReturnService {
    ResponseEntity<?> returnBook(String bookId, ReturnRequest request);
}