package med.voll.api.domain.patient;

import med.voll.api.domain.address.Address;

public record PatientDetailedData(Long id, String nome, String email, String cpf, String telefone, Address endereco) {

    public PatientDetailedData(Patient patient){
        this(patient.getId(), patient.getNome(), patient.getEmail(), patient.getCpf(), patient.getTelefone(), patient.getEndereco());
    }
}
