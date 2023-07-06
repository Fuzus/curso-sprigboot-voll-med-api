package med.voll.api.domain.medic;

public record MedicListData(Long id, String nome, String emial, String crm, Specialty especialidade) {

    public MedicListData(Medic medic){
        this(medic.getId(), medic.getNome(), medic.getEmail(), medic.getCrm(), medic.getEspecialidade());
    }
}
