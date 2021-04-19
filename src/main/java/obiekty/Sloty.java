package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Sloty")
public class Sloty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_slota;
    @OneToOne
    @JoinColumn(name="id_wizyty")
    private Wizyty wizyta;
    private int miesiac;
    private int dzien;
    private int godzina;
    private String informacja;
}
