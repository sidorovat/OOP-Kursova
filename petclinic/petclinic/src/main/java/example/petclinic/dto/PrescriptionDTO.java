package example.petclinic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrescriptionDTO {
  @NotNull(message = "Visit ID is required")
  private Long visitId;

  @NotBlank(message = "Medication is required")
  private String medication;

  @NotBlank(message = "Dosage is required")
  private String dosage;
}