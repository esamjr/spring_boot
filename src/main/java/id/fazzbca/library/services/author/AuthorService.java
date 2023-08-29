package id.fazzbca.library.services.author;

import org.springframework.http.ResponseEntity;

import id.fazzbca.library.payloads.req.AuthorRequest;

public interface AuthorService {
  // kerangka crud methodnya
  // create
  ResponseEntity<?> addAuthorService(AuthorRequest request);

  // find all authors
  ResponseEntity<?> getAuthorsService();

  // find by id
  ResponseEntity<?> getAuthorByIdService(String id);

  // update author
  ResponseEntity<?> updateAuthorByIdService(String id, AuthorRequest request);

  // delete author
  ResponseEntity<?> deleteAuthorByIdService(String id);

  // find by name
  ResponseEntity<?> getAuthorByNameService(String name);
}
