package example.petclinic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OwnerDTO {
  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Phone is required")
  private String phone;
}