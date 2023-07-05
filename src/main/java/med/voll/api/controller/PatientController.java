package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patient.Patient;
import med.voll.api.patient.PatientListData;
import med.voll.api.patient.RegisterPatientData;
import med.voll.api.patient.UpdatePatientData;
import med.voll.api.repository.PatitentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    @Autowired
    private PatitentRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterPatientData obj){
        repository.save(new Patient(obj));
    }

    @GetMapping
    public Page<PatientListData> listAll(@PageableDefault(size = 10, sort = "nome") Pageable pagination){
        return repository.findAllByAtivoTrue(pagination).map(PatientListData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdatePatientData obj){
        var patient = repository.getReferenceById(obj.id());
        patient.updateData(obj);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var patient = repository.getReferenceById(id);
        patient.delete();
    }
}
