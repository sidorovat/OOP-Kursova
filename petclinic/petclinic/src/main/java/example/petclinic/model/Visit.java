package example.petclinic.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "visits")
@Data
public class Visit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "pet_id", nullable = false)
  private Pet pet;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private String reason;
}