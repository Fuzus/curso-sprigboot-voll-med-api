package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medic.*;
import med.voll.api.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterMedicData obj, UriComponentsBuilder uriBuilder){
        var medic = new Medic(obj);
        repository.save(medic);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medic.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicDetailedData(medic));
    }

    @GetMapping
    public ResponseEntity<Page<MedicListData>> listAll(
            @PageableDefault(size = 10, sort = {"nome"})
            Pageable pagination){
        var page = repository.findAllByAtivoTrue(pagination).map(MedicListData::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateMedicData obj){
        var medic = repository.getReferenceById(obj.id());
        medic.updateData(obj);

        return ResponseEntity.ok(new MedicDetailedData(medic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var medic = repository.getReferenceById(id);
        medic.delete();
        return ResponseEntity.noContent().build();
    }
}
