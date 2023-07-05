package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medic.Medic;
import med.voll.api.medic.MedicListData;
import med.voll.api.medic.RegisterMedicData;
import med.voll.api.medic.UpdateMedicData;
import med.voll.api.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterMedicData obj){
        repository.save(new Medic(obj));
    }

    @GetMapping
    public Page<MedicListData> listAll(
            @PageableDefault(size = 10, sort = {"nome"})
            Pageable page){
        return repository.findAllByAtivoTrue(page).map(MedicListData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateMedicData obj){
        var medic = repository.getReferenceById(obj.id());
        medic.updateData(obj);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var medic = repository.getReferenceById(id);
        medic.delete();
    }
}
