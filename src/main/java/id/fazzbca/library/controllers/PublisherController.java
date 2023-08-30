package id.fazzbca.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.fazzbca.library.payloads.req.PublisherRequest;
import id.fazzbca.library.payloads.res.ResponseHandler;
import id.fazzbca.library.services.publisher.PublisherService;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
  @Autowired
  PublisherService publisherService;

  @PostMapping
  public ResponseEntity<?> createPublisher(@RequestBody PublisherRequest request) {
    try {
      return publisherService.addPublisherService(request);
    } catch (Exception e) {
      return ResponseHandler.responseError(500, e.getMessage(), null);
    }
  }

  @GetMapping
  public ResponseEntity<?> getPublishers(@RequestParam(value = "deleted", defaultValue = "") Boolean isDeleted,
      @RequestParam(value = "name") String namePublisher) {
    try {
      return publisherService.getPublishersService(isDeleted);
    } catch (Exception e) {
      return ResponseHandler.responseError(500, e.getMessage(), null);
    }
  }

  @GetMapping("/filter")
  public ResponseEntity<?> getPublishers(@RequestParam(value = "name") String name) {
    try {
      return publisherService.getPublishersByNameService(name);
    } catch (Exception e) {
      return ResponseHandler.responseError(500, e.getMessage(), null);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updatePublisher(@PathVariable(value = "id") String id,
      @RequestBody PublisherRequest request) {
    try {
      return publisherService.updatePublisherService(id, request);
    } catch (Exception e) {
      return ResponseHandler.responseError(500, e.getMessage(), null);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePublisher(@PathVariable(value = "id") String id) {
    try {
      return publisherService.deletePublisherService(id);
    } catch (Exception e) {
      return ResponseHandler.responseError(500, e.getMessage(), null);
    }
  }
}
