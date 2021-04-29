package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Wizyty")
public class Wizyty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_wizyty;
    @OneToOne
    @JoinColumn(name="id_kalendarza")
    private Kalendarz kalendarz;
    @OneToOne
    @JoinColumn(name="id_pacjenta")
    private Pacjent pacjent;

    public Wizyty() {
    }

    public Wizyty(Kalendarz kalendarz, Pacjent pacjent) {
        this.kalendarz = kalendarz;
        this.pacjent = pacjent;
    }

    public Kalendarz getKalendarz() {
        return kalendarz;
    }

    public void setKalendarz(Kalendarz kalendarz) {
        this.kalendarz = kalendarz;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }
}
