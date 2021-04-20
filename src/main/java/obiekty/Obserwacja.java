package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Obserwacje")
public class Obserwacja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_obserwacji;
    private String tekst;
    @OneToOne
    @JoinColumn(name="id_choroby")
    private Lista_chorob choroba;
    @OneToOne
    @JoinColumn(name="id_pracownika")
    private Pracownik pracownik;
    @OneToOne
    @JoinColumn(name="id_pacjenta")
    private Pacjent pacjent;
}
