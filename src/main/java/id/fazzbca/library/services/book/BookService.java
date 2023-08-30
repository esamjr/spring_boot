package id.fazzbca.library.services.book;

import org.springframework.http.ResponseEntity;

import id.fazzbca.library.payloads.req.BookRequest;

public interface BookService {
  // create book
  ResponseEntity<?> addBookService(BookRequest request);

  // get all books or by status deleted
  ResponseEntity<?> getBooksService(Boolean isDeleted);

  // get book by id
  ResponseEntity<?> getBookByIdService(String id);

  // update book by id
  ResponseEntity<?> updateBookService(String id, BookRequest request);

  // delete book by id
  ResponseEntity<?> deleteBookService(String id);
  ResponseEntity<?> getBorrowedBooksInfo();

}
