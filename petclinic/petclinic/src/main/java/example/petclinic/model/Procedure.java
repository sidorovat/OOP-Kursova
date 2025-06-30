package example.petclinic.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "procedures")
@Data
public class Procedure {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "visit_id", nullable = false)
  private Visit visit;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private BigDecimal cost;
}