package example.petclinic.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "prescriptions")
@Data
public class Prescription {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "visit_id", nullable = false)
  private Visit visit;

  @Column(nullable = false)
  private String medication;

  @Column(nullable = false)
  private String dosage;
}