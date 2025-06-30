package example.petclinic.controller;

import example.petclinic.dto.report.ProcedureStatsDTO;
import example.petclinic.dto.report.SpeciesStatsDTO;
import example.petclinic.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
  private final ReportService reportService;

  @GetMapping("/procedure-count")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<List<ProcedureStatsDTO>> getProcedureStats(@RequestParam String month) {
    return ResponseEntity.ok(reportService.getProcedureStats(month));
  }

  @GetMapping("/species-stats")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<List<SpeciesStatsDTO>> getSpeciesStats() {
    return ResponseEntity.ok(reportService.getSpeciesStats());
  }

  @GetMapping("/total-cost/{petId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<BigDecimal> getTotalCostByPet(@PathVariable Long petId) {
    return ResponseEntity.ok(reportService.getTotalCostByPet(petId));
  }
}