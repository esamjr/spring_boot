package id.fazzbca.library.services.book;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.fazzbca.library.models.Author;
import id.fazzbca.library.models.Book;
import id.fazzbca.library.models.Publisher;
import id.fazzbca.library.payloads.req.BookRequest;
import id.fazzbca.library.payloads.res.ResponseHandler;
import id.fazzbca.library.repositories.AuthorRepository;
import id.fazzbca.library.repositories.BookRepository;
import id.fazzbca.library.repositories.PublisherRepository;
import id.fazzbca.library.validators.AuthorValidation;
import id.fazzbca.library.validators.PublisherValidation;

@Service
public class BookServiceImpl implements BookService {
  @Autowired
  BookRepository bookRepository;

  @Autowired
  AuthorRepository authorRepository;

  @Autowired
  PublisherRepository publisherRepository;

  @Autowired
  AuthorValidation authorValidation;

  @Autowired
  PublisherValidation publisherValidation;

  @Override
  public ResponseEntity<?> addBookService(BookRequest request) {
    // cek inputan
    // mencari data author dan penerbit dari request yg masuk
    // bisa diimprove request author dan penerbitnya menggunakan idnya
    Author author = authorRepository.findByName(request.getNamaPengarang());
    // setelah melakukan pencarian, harus divalidasi dulu ada atau tidak
    authorValidation.validateAuthor(author);

    Publisher publisher = publisherRepository.findByName(request.getNamaPenerbit());
    publisherValidation.validatePublisher(publisher);

    // buatkan obj entitas dari bukunya
    Book book = new Book(request.getJudul(), request.getTahunTerbit(), author, publisher);

    // save ke db
    bookRepository.save(book);

    // return response
    return ResponseHandler.responseMessage(201, "Book successfully added!", true);
  }

  @Override
  public ResponseEntity<?> getBooksService(Boolean isDeleted) {
    List<Book> books = new ArrayList<>();
    // cek is deleted null atau tidak
    if (isDeleted == null) {
      books = bookRepository.findAll();
    } else {
      books = bookRepository.findByIsDeleted(isDeleted);
    }

    // return response data
    return ResponseHandler.responseData(200, "success", books);
  }

  @Override
  public ResponseEntity<?> getBookByIdService(String id) {
    Book book = bookRepository.findById(id).orElseThrow(() -> {
      throw new NoSuchElementException("id book is not found!");
    });

    return ResponseHandler.responseData(200, "success", book);
  }

  @Override
  public ResponseEntity<?> updateBookService(String id, BookRequest request) {
    Book book = bookRepository.findById(id).orElseThrow(() -> {
      throw new NoSuchElementException("id book is not found!");
    });

    if (request.getJudul() != "") {
      book.setTitle(request.getJudul());
    }

    if (request.getTahunTerbit() != "") {
      book.setYear(request.getTahunTerbit());
    }

    if (request.getNamaPengarang() != "") {
      Author author = authorRepository.findByName(request.getNamaPengarang());
      authorValidation.validateAuthor(author);
      book.setAuthor(author);
    }

    if (request.getNamaPenerbit() != "") {
      Publisher publisher = publisherRepository.findByName(request.getNamaPenerbit());
      publisherValidation.validatePublisher(publisher);
      book.setPublisher(publisher);
    }

    // save ke db
    bookRepository.save(book);

    return ResponseHandler.responseMessage(200, "Book successfully updated", true);
  }

  @Override
  public ResponseEntity<?> deleteBookService(String id) {
    Book book = bookRepository.findById(id).orElseThrow(() -> {
      throw new NoSuchElementException("id book is not found!");
    });

    book.setIsDeleted(true);
    bookRepository.save(book);

    return ResponseHandler.responseMessage(200, "Successfully deleted!", true);
  }

}
