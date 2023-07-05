package med.voll.api.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    private String cpf;
    @Embedded
    private Address endereco;

    private Boolean ativo;

    public Patient(RegisterPatientData data){
        this.ativo = true;
        this.nome = data.nome();
        this.email = data.email();
        this.telefone = data.telefone();
        this.cpf = data.cpf();
        this.endereco = new Address(data.endereco());
    }

    public void updateData(UpdatePatientData obj) {
        if (obj.nome() != null)
            this.nome = obj.nome();
        if (obj.telefone() != null)
            this.telefone = obj.telefone();
        if (obj.endereco() != null)
            this.endereco.updateData(obj.endereco());
    }

    public void delete() {
        this.ativo = false;
    }
}
