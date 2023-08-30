package id.fazzbca.library.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "returnings")
@Data
public class Returning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "borrowing_id")
    private Borrowing borrowing;

    @Column(name = "returned_at")
    private LocalDateTime returnedAt;
}
