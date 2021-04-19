package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Kalendarz")
public class Kalendarz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_kalendarza ;
    private int nr_tygodnia;
    private int rok;
    @OneToOne
    @JoinColumn(name="id_pracownika")
    private Pracownik pracownik;


}
