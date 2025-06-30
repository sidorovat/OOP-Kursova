package example.petclinic.dto.report;

import lombok.Data;

@Data
public class SpeciesStatsDTO {
  private String species;
  private Long count;

  public SpeciesStatsDTO(String species, Long count) {
    this.species = species;
    this.count = count;
  }
}