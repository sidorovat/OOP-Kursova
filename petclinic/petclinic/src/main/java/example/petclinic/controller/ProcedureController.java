package example.petclinic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import example.petclinic.dto.ProcedureDTO;
import example.petclinic.model.Procedure;
import example.petclinic.service.ProcedureService;

import java.util.List;

@RestController
@RequestMapping("/api/procedures")
@RequiredArgsConstructor
public class ProcedureController {
  private final ProcedureService procedureService;

  @GetMapping
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<List<Procedure>> getAllProcedures() {
    return ResponseEntity.ok(procedureService.getAllProcedures());
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Procedure> createProcedure(@RequestBody ProcedureDTO dto) {
    return ResponseEntity.ok(procedureService.createProcedure(dto));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Procedure> updateProcedure(@PathVariable Long id, @RequestBody ProcedureDTO dto) {
    return ResponseEntity.ok(procedureService.updateProcedure(id, dto));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteProcedure(@PathVariable Long id) {
    procedureService.deleteProcedure(id);
    return ResponseEntity.noContent().build();
  }
}