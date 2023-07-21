package med.voll.api.domain.medic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicRepository extends JpaRepository<Medic, Long> {

    Page<Medic> findAllByAtivoTrue(Pageable pagination);

    @Query("""
            select m from Medic m
            where
                m.ativo = 1
                and m.especialidade = :especialidade
                and m.id not in (
                    select c.medic.id from Appointment c
                    where c.date = :date
                    and c.reason is null
                )
            order by rand()
            limit 1
            """)
    Medic chooseRandomFreeMedic(Specialty especialidade, LocalDateTime date);

    @Query("""
            select m.ativo from Medic m
            where m.id = :idMedico
            """)
    Boolean findAtivoById(Long idMedico);
}
