package example.petclinic.repository;

import example.petclinic.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
  List<Procedure> findByVisitId(Long visitId);
}