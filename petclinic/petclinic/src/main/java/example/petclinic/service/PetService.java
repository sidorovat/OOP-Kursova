package example.petclinic.service;

import example.petclinic.dto.PetDTO;
import example.petclinic.model.Pet;
import example.petclinic.repository.PetRepository;
import example.petclinic.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
  private final PetRepository petRepository;
  private final OwnerRepository ownerRepository;

  public List<Pet> getPetsByOwner(Long ownerId) {
    return petRepository.findByOwnerId(ownerId);
  }

  public Pet createPet(PetDTO dto) {
    Pet pet = new Pet();
    pet.setName(dto.getName());
    pet.setSpecies(dto.getSpecies());
    pet.setBirthDate(dto.getBirthDate());
    pet.setOwner(ownerRepository.findById(dto.getOwnerId())
        .orElseThrow(() -> new RuntimeException("Owner not found")));
    return petRepository.save(pet);
  }

  public Pet updatePet(Long id, PetDTO dto) {
    Pet pet = petRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pet not found"));
    pet.setName(dto.getName());
    pet.setSpecies(dto.getSpecies());
    pet.setBirthDate(dto.getBirthDate());
    pet.setOwner(ownerRepository.findById(dto.getOwnerId())
        .orElseThrow(() -> new RuntimeException("Owner not found")));
    return petRepository.save(pet);
  }

  public void deletePet(Long id) {
    petRepository.deleteById(id);
  }
}