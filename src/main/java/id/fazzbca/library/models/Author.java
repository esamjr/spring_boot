package id.fazzbca.library.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
  @Id
  @UuidGenerator
  private String id;

  @Column(length = 100)
  private String name;

  @Column(length = 100)
  private String socialMedia;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  private Boolean isDeleted = false;

  public Author(String name, String socialMedia) {
    this.name = name;
    this.socialMedia = socialMedia;
  }
}
