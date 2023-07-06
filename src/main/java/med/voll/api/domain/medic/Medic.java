package med.voll.api.domain.medic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Specialty especialidade;
    @Embedded
    private Address endereco;

    private boolean ativo;

    public Medic(RegisterMedicData obj) {
        this.ativo = true;
        this.nome = obj.nome();
        this.email = obj.email();
        this.telefone = obj.telefone();
        this.crm = obj.crm();
        this.especialidade = obj.especialidade();
        this.endereco = new Address(obj.endereco());
    }

    public void updateData(UpdateMedicData obj) {
        if (obj.nome() != null)
            this.nome = obj.nome();
        if(obj.telefone() != null)
            this.telefone = obj.telefone();
        if (obj.endereco() != null)
            this.endereco.updateData(obj.endereco());
    }

    public void delete() {
        this.ativo = false;
    }
}
