package example.petclinic.service;

import example.petclinic.dto.VisitDTO;
import example.petclinic.model.Visit;
import example.petclinic.repository.VisitRepository;
import example.petclinic.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {
  private final VisitRepository visitRepository;
  private final PetRepository petRepository;

  public List<Visit> getAllVisits() {
    return visitRepository.findAll();
  }

  public List<Visit> getVisitsByPet(Long petId) {
    return visitRepository.findByPetId(petId);
  }

  public List<Visit> getVisitsByDate(LocalDate date) {
    return visitRepository.findByDate(date);
  }

  public Visit getLastVisitByPetId(Long petId) {
    return visitRepository.findTopByPetIdOrderByDateDesc(petId);
  }

  public Visit createVisit(VisitDTO dto) {
    Visit visit = new Visit();
    visit.setPet(petRepository.findById(dto.getPetId())
        .orElseThrow(() -> new RuntimeException("Pet not found")));
    visit.setDate(dto.getDate());
    visit.setReason(dto.getReason());
    return visitRepository.save(visit);
  }

  public Visit updateVisit(Long id, VisitDTO dto) {
    Visit visit = visitRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Visit not found"));
    visit.setPet(petRepository.findById(dto.getPetId())
        .orElseThrow(() -> new RuntimeException("Pet not found")));
    visit.setDate(dto.getDate());
    visit.setReason(dto.getReason());
    return visitRepository.save(visit);
  }

  public void deleteVisit(Long id) {
    visitRepository.deleteById(id);
  }
}