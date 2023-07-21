package med.voll.api.domain.appointment;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.medic.Medic;
import med.voll.api.domain.patient.Patient;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medic medic;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Patient patient;
    @Column(name = "data")
    private LocalDateTime date;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private CancelReason reason;

    public void setReason(CancelReason reason){
        this.reason = reason;
    }

}
