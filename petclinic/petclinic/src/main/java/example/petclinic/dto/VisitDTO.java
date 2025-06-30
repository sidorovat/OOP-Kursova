package example.petclinic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class VisitDTO {
  @NotNull(message = "Pet ID is required")
  private Long petId;

  @NotNull(message = "Date is required")
  private LocalDate date;

  @NotBlank(message = "Reason is required")
  private String reason;
}