package example.petclinic.controller;

import example.petclinic.dto.PrescriptionDTO;
import example.petclinic.model.Prescription;
import example.petclinic.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {
  private final PrescriptionService prescriptionService;

  @GetMapping("/visit/{visitId}")
  @PreAuthorize("hasRole('USER')")
  public List<Prescription> getByVisit(@PathVariable Long visitId) {
    return prescriptionService.getByVisit(visitId);
  }

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public Prescription create(@RequestBody PrescriptionDTO dto) {
    return prescriptionService.createPrescription(dto);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('USER')")
  public Prescription update(@PathVariable Long id, @RequestBody PrescriptionDTO dto) {
    return prescriptionService.updatePrescription(id, dto);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('USER')")
  public void delete(@PathVariable Long id) {
    prescriptionService.deletePrescription(id);
  }
}