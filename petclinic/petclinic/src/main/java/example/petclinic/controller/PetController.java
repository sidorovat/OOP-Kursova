package example.petclinic.controller;

import example.petclinic.dto.PetDTO;
import example.petclinic.model.Pet;
import example.petclinic.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {
  private final PetService petService;

  @GetMapping("/owner/{ownerId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<List<Pet>> getPetsByOwner(@PathVariable Long ownerId) {
    return ResponseEntity.ok(petService.getPetsByOwner(ownerId));
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Pet> createPet(@Valid @RequestBody PetDTO dto) {
    return ResponseEntity.ok(petService.createPet(dto));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Pet> updatePet(@PathVariable Long id, @Valid @RequestBody PetDTO dto) {
    return ResponseEntity.ok(petService.updatePet(id, dto));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deletePet(@PathVariable Long id) {
    petService.deletePet(id);
    return ResponseEntity.noContent().build();
  }
}