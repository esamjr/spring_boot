package id.fazzbca.library.validators;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.stereotype.Component;
import id.fazzbca.library.models.Publisher;

@Component
public class PublisherValidation {
  public void validatePublisher(Publisher publisher) {
    if (publisher == null || Objects.isNull(publisher)) {
      throw new NoSuchElementException("Publisher is not found!");
    }
  }
}
