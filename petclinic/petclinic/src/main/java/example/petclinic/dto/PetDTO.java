package example.petclinic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PetDTO {
  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Species is required")
  private String species;

  private LocalDate birthDate;

  @NotNull(message = "Owner ID is required")
  private Long ownerId;
}