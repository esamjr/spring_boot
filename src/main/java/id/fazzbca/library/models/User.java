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
@Table(name = "users")
public class User {
  @Id
  @UuidGenerator
  private String id;

  @Column(length = 50, unique = true)
  private String username;

  @Column(length = 100)
  private String email;

  @Column(length = 100)
  private String password;

  private Integer role;
  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  private Boolean isDeleted = false;

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
