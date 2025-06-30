package example.petclinic.controller;

import example.petclinic.model.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import example.petclinic.dto.OwnerDTO;
import example.petclinic.service.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {
  private final OwnerService ownerService;

  @GetMapping
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<List<Owner>> getAllOwners() {
    return ResponseEntity.ok(ownerService.getAllOwners());
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Owner> createOwner(@RequestBody OwnerDTO dto) {
    return ResponseEntity.ok(ownerService.createOwner(dto));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody OwnerDTO dto) {
    return ResponseEntity.ok(ownerService.updateOwner(id, dto));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
    ownerService.deleteOwner(id);
    return ResponseEntity.noContent().build();
  }
}
