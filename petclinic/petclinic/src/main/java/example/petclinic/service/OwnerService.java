package example.petclinic.service;

import example.petclinic.dto.OwnerDTO;
import example.petclinic.model.Owner;
import example.petclinic.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {
  private final OwnerRepository ownerRepository;

  public List<Owner> getAllOwners() {
    return ownerRepository.findAll();
  }

  public Owner createOwner(OwnerDTO dto) {
    Owner owner = new Owner();
    owner.setName(dto.getName());
    owner.setPhone(dto.getPhone());
    return ownerRepository.save(owner);
  }

  public Owner updateOwner(Long id, OwnerDTO dto) {
    Owner owner = ownerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Owner not found"));
    owner.setName(dto.getName());
    owner.setPhone(dto.getPhone());
    return ownerRepository.save(owner);
  }

  public void deleteOwner(Long id) {
    ownerRepository.deleteById(id);
  }
}