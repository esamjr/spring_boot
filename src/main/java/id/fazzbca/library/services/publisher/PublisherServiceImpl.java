package id.fazzbca.library.services.publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.fazzbca.library.models.Publisher;
import id.fazzbca.library.payloads.req.PublisherRequest;
import id.fazzbca.library.payloads.res.ResponseHandler;
import id.fazzbca.library.repositories.PublisherRepository;

@Service
public class PublisherServiceImpl implements PublisherService {
  @Autowired
  PublisherRepository publisherRepository;

  @Override
  public ResponseEntity<?> addPublisherService(PublisherRequest request) {
    // cek input name required
    if (request.getName() == null || request.getName() == "") {
      throw new IllegalArgumentException("Name publisher is required!");
    }

    // convert request menjadi model entity
    Publisher publisher = new Publisher(request.getName(), request.getAddress());

    // save ke db
    publisherRepository.save(publisher);

    // return response
    return ResponseHandler.responseMessage(201, "Publisher successfully added!", true);
  }

  @Override
  public ResponseEntity<?> getPublishersService(Boolean isDeleted) {
    // instance list publishers
    List<Publisher> publishers = new ArrayList<>();

    // cek apakah isdeleted datang dengan value?
    if (isDeleted == null) {
      // kalo isdeleted null, maka kita get all data
      publishers = publisherRepository.findAll();
    } else {
      // kalo ada valuenya, find by isdeletednya
      publishers = publisherRepository.findByIsDeleted(isDeleted);
    }

    // return response
    return ResponseHandler.responseData(200, "success", publishers);
  }

  @Override
  public ResponseEntity<?> getPublishersByNameService(String name) {
    List<Publisher> publishers = publisherRepository.findByNameContaining(name);
    return ResponseHandler.responseData(200, "success", publishers);
  }

  @Override
  public ResponseEntity<?> updatePublisherService(String id, PublisherRequest request) {
    // find by id
    Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> {
      throw new NoSuchElementException("id is not found!");
    });

    // update data
    if (request.getName() != "") {
      publisher.setName(request.getName());
    }

    if (request.getAddress() != "") {
      publisher.setAddress(request.getAddress());
    }

    // save ke db
    publisherRepository.save(publisher);

    // return response
    return ResponseHandler.responseMessage(200, "Publisher successfully updated", true);
  }

  @Override
  public ResponseEntity<?> deletePublisherService(String id) {
    // find by id
    Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> {
      throw new NoSuchElementException("id is not found!");
    });

    // set is deleted menjadi true
    publisher.setIsDeleted(true);

    // save ke db
    publisherRepository.save(publisher);

    // return response
    return ResponseHandler.responseMessage(200, "Publisher successfully deleted!", true);
  }

}
