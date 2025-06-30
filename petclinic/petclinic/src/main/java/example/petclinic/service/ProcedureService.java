package example.petclinic.service;

import example.petclinic.dto.ProcedureDTO;
import example.petclinic.model.Procedure;
import example.petclinic.model.Visit;
import example.petclinic.repository.ProcedureRepository;
import example.petclinic.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcedureService {
  private final ProcedureRepository procedureRepository;
  private final VisitRepository visitRepository;

  public Procedure createProcedure(@Valid ProcedureDTO procedureDTO) {
    Visit visit = visitRepository.findById(procedureDTO.getVisitId())
        .orElseThrow(() -> new IllegalArgumentException("Visit not found"));
    Procedure procedure = new Procedure();
    procedure.setVisit(visit);
    procedure.setName(procedureDTO.getName());
    procedure.setCost(procedureDTO.getCost());
    return procedureRepository.save(procedure);
  }

  public List<Procedure> getProceduresByVisitId(Long visitId) {
    return procedureRepository.findByVisitId(visitId);
  }
}