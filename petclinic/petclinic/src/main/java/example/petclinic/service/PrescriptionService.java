package example.petclinic.service;

import example.petclinic.dto.PrescriptionDTO;
import example.petclinic.model.Prescription;
import example.petclinic.model.Visit;
import example.petclinic.repository.PrescriptionRepository;
import example.petclinic.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
  private final PrescriptionRepository prescriptionRepository;
  private final VisitRepository visitRepository;

  public Prescription createPrescription(@Valid PrescriptionDTO prescriptionDTO) {
    Visit visit = visitRepository.findById(prescriptionDTO.getVisitId())
        .orElseThrow(() -> new IllegalArgumentException("Visit not found"));
    Prescription prescription = new Prescription();
    prescription.setVisit(visit);
    prescription.setMedication(prescriptionDTO.getMedication());
    prescription.setDosage(prescriptionDTO.getDosage());
    return prescriptionRepository.save(prescription);
  }

  public List<Prescription> getPrescriptionsByVisitId(Long visitId) {
    return prescriptionRepository.findByVisitId(visitId);
  }
}