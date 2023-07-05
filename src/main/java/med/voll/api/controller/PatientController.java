package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patient.*;
import med.voll.api.repository.PatitentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    @Autowired
    private PatitentRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterPatientData obj, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(obj);
        repository.save(patient);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDetailedData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListData>> listAll(@PageableDefault(size = 10, sort = "nome") Pageable pagination) {
        var page = repository.findAllByAtivoTrue(pagination).map(PatientListData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailedData> getData(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailedData(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdatePatientData obj) {
        var patient = repository.getReferenceById(obj.id());
        patient.updateData(obj);

        return ResponseEntity.ok(new PatientDetailedData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();

        return ResponseEntity.noContent().build();
    }
}
