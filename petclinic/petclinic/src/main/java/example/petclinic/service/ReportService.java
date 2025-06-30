package example.petclinic.service;

import example.petclinic.dto.report.ProcedureStatsDTO;
import example.petclinic.dto.report.SpeciesStatsDTO;
import example.petclinic.repository.ProcedureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
  private final ProcedureRepository procedureRepository;

  public List<ProcedureStatsDTO> getProcedureStats(String month) {
    // Фільтрація за місяцем може бути додана через JPQL-запит
    return procedureRepository.findProcedureStats().stream()
        .map(arr -> new ProcedureStatsDTO((String) arr[0], (Long) arr[1]))
        .collect(Collectors.toList());
  }

  public List<SpeciesStatsDTO> getSpeciesStats() {
    return procedureRepository.findSpeciesStats().stream()
        .map(arr -> new SpeciesStatsDTO((String) arr[0], (Long) arr[1]))
        .collect(Collectors.toList());
  }

  public BigDecimal getTotalCostByPet(Long petId) {
    return procedureRepository.findTotalCostByPetId(petId);
  }
}