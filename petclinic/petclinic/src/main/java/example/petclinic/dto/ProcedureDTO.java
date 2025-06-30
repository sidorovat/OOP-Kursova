package example.petclinic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProcedureDTO {
  @NotNull(message = "Visit ID is required")
  private Long visitId;

  @NotBlank(message = "Name is required")
  private String name;

  @NotNull(message = "Cost is required")
  private BigDecimal cost;
}