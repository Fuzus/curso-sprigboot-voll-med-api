package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Address(AddressData obj) {
        this.logradouro = obj.logradouro();
        this.bairro = obj.bairro();
        this.cep = obj.cep();
        this.uf = obj.uf();
        this.cidade = obj.cidade();
        this.complemento = obj.complemento();
        this.numero = obj.numero();
    }

    public void updateData(AddressData data) {
        if (data.logradouro() != null)
            this.logradouro = data.logradouro();
        if (data.cep() != null)
            this.cep = data.cep();
        if (data.cidade() != null)
            this.cidade = data.cidade();
        if (data.bairro() != null)
            this.bairro = data.bairro();
        if (data.uf() != null)
            this.uf = data.uf();
        if (data.complemento() != null)
            this.complemento = data.complemento();
        if (data.numero() != null)
            this.numero = data.numero();
    }
}
