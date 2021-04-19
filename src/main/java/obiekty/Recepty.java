package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Recepty")
public class Recepty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_recepty;
    private String nazwa;

    @OneToOne
    @JoinColumn(name="id_pracownika")
    private Pracownik pracownik;

    @OneToOne
    @JoinColumn(name="id_pacjenta")
    private Pacjent pacjent;
}
