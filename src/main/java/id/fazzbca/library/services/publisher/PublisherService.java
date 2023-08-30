package id.fazzbca.library.services.publisher;

import org.springframework.http.ResponseEntity;

import id.fazzbca.library.payloads.req.PublisherRequest;

public interface PublisherService {
  // CRUD PUBLISHER
  // CREATE, READ ALL, READ BY ID, READ BY NAMA PENERBIT, READ BY DELETED
  // UPDATE, DELETE

  // create publisher
  ResponseEntity<?> addPublisherService(PublisherRequest request);

  // read all with filter deleted
  ResponseEntity<?> getPublishersService(Boolean isDeleted);

  // read by nama penerbit
  ResponseEntity<?> getPublishersByNameService(String name);

  // update
  ResponseEntity<?> updatePublisherService(String id, PublisherRequest request);

  // delete
  ResponseEntity<?> deletePublisherService(String id);
}
