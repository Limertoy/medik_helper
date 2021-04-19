package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Skierowanie")
public class Skierowanie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_skierowania;
    private String poradnia;
    private String rozpoznanie;
    private String informacje;
    @OneToOne
    @JoinColumn(name="id_pacjenta")
    private Pacjent pacjent;
}
