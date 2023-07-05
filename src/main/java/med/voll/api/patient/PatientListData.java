package med.voll.api.patient;

public record PatientListData(Long id, String nome, String email, String cpf) {

    public PatientListData(Patient patient){
        this(patient.getId(), patient.getNome(), patient.getEmail(), patient.getCpf());
    }
}
