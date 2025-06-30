package example.petclinic.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "pets")
@Data
public class Pet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String species;

  private LocalDate birthDate;

  @ManyToOne
  @JoinColumn(name = "owner_id", nullable = false)
  private Owner owner;
}