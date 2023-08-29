package id.fazzbca.library.services.author;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.fazzbca.library.models.Author;
import id.fazzbca.library.payloads.req.AuthorRequest;
import id.fazzbca.library.payloads.res.ResponseHandler;
import id.fazzbca.library.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
  @Autowired
  AuthorRepository authorRepository;

  @Override
  public ResponseEntity<?> addAuthorService(AuthorRequest request) {
    // cek request, ada yg null atau tidak
    // semisal null field req, nanti akn throw exception
    if (request.getName() == null || request.getName() == "") {
      throw new IllegalArgumentException("Name is required!");
    }

    // membuatkan obj entitas dari obj request
    Author author = new Author(request.getName(), request.getSocialMedia());
    // save ke db
    authorRepository.save(author);

    // buatkan response
    return ResponseHandler.responseData(HttpStatus.CREATED.value(), "Author successfully added!", null);
  }

  @Override
  public ResponseEntity<?> getAuthorsService() {
    // find all authors dari repository
    List<Author> authors = authorRepository.findAll();

    return ResponseHandler.responseData(200, "success", authors);
  }

  @Override
  public ResponseEntity<?> getAuthorByIdService(String id) {
    // cari idnya ada atau ngga?
    // ngga ada maka akan dithrow
    if (!authorRepository.existsById(id)) {
      throw new NoSuchElementException("id is not found!");
    }

    // find data author
    Author author = authorRepository.findById(id).orElseThrow(() -> {
      throw new NoSuchElementException("id is not found!");
    });

    return ResponseHandler.responseData(200, "success", author);
  }

  @Override
  public ResponseEntity<?> updateAuthorByIdService(String id, AuthorRequest request) {
    // find author
    Author author = authorRepository.findById(id).orElseThrow(() -> {
      throw new NoSuchElementException("id is not found!");
    });

    // ubah data authornya pakai setter
    if (request.getName() != "") {
      author.setName(request.getName());
    }

    if (request.getSocialMedia() != "") {
      author.setSocialMedia(request.getSocialMedia());
    }

    // save ke db
    authorRepository.save(author);

    // return response data
    return ResponseHandler.responseData(200, "Author successfully updated", author);
  }

  @Override
  public ResponseEntity<?> deleteAuthorByIdService(String id) {
    // find id
    Author author = authorRepository.findById(id).orElseThrow(() -> {
      throw new NoSuchElementException("id is not found!");
    });

    // ubah field is deleted menjadi true
    author.setIsDeleted(true);

    // save ke db
    authorRepository.save(author);

    // return response
    return ResponseHandler.responseData(200, "Author successfully deleted!", null);
  }

  @Override
  public ResponseEntity<?> getAuthorByNameService(String name) {
    // find data
    // List<Author> authors = authorRepository.findByNameContaining(name);
    List<Author> authors = authorRepository.getAuthorByName(name);

    return ResponseHandler.responseData(200, "success", authors);
  }

}
