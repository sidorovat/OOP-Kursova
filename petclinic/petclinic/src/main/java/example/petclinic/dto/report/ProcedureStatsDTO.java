package example.petclinic.dto.report;

import lombok.Data;

@Data
public class ProcedureStatsDTO {
  private String procedureName;
  private Long count;

  public ProcedureStatsDTO(String procedureName, Long count) {
    this.procedureName = procedureName;
    this.count = count;
  }
}