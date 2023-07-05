package med.voll.api.medic;

import med.voll.api.address.Address;

public record MedicDetailedData(Long id, String nome, String email, String crm, String telefone, Specialty especialidade, Address endereco) {

    public MedicDetailedData(Medic obj){
        this(obj.getId(), obj.getNome(), obj.getEmail(), obj.getCrm(), obj.getTelefone(), obj.getEspecialidade(), obj.getEndereco());
    }
}
