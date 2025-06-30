package example.petclinic.repository;

import example.petclinic.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
  List<Prescription> findByVisitId(Long visitId);
}