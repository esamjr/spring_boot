package id.fazzbca.library.models;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Book {
  private String id;
  private String title;
  private String year;
  private Author author;
  private Publisher publisher;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Boolean isDeleted = false;

  public Book(String title, String year, Author author, Publisher publisher) {
    this.title = title;
    this.year = year;
    this.author = author;
    this.publisher = publisher;
  }
}
