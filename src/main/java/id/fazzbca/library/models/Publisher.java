package id.fazzbca.library.models;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Publisher {
  private String id;
  private String name;
  private String address;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Boolean isDeleted = false;

  public Publisher(String name, String address) {
    this.name = name;
    this.address = address;
  }
}
