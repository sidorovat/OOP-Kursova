package example.petclinic.controller;

import example.petclinic.dto.VisitDTO;
import example.petclinic.model.Visit;
import example.petclinic.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/visits")
@RequiredArgsConstructor
public class VisitController {
  private final VisitService visitService;

  @GetMapping
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<List<Visit>> getAllVisits() {
    return ResponseEntity.ok(visitService.getAllVisits());
  }

  @GetMapping("/pet/{petId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<List<Visit>> getVisitsByPet(@PathVariable Long petId) {
    return ResponseEntity.ok(visitService.getVisitsByPet(petId));
  }

  @GetMapping("/by-date")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<List<Visit>> getVisitsByDate(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return ResponseEntity.ok(visitService.getVisitsByDate(date));
  }

  @GetMapping("/latest/{petId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<Visit> getLatestVisit(@PathVariable Long petId) {
    return ResponseEntity.ok(visitService.getLastVisitByPetId(petId));
  }

  @PostMapping
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<Visit> createVisit(@RequestBody VisitDTO dto) {
    return ResponseEntity.ok(visitService.createVisit(dto));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<Visit> updateVisit(@PathVariable Long id, @RequestBody VisitDTO dto) {
    return ResponseEntity.ok(visitService.updateVisit(id, dto));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
    visitService.deleteVisit(id);
    return ResponseEntity.noContent().build();
  }
}