package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Kalendarz")
public class Kalendarz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_kalendarza;
    private int nr_tygodnia;
    private int rok;
    @OneToOne
    @JoinColumn(name="id_pracownika")
    private Pracownik pracownik;

    public Kalendarz(int nr_tygodnia, int rok, Pracownik pracownik) {
        this.nr_tygodnia = nr_tygodnia;
        this.rok = rok;
        this.pracownik = pracownik;
    }

    public Kalendarz() {
    }

    public int getNr_tygodnia() {
        return nr_tygodnia;
    }

    public void setNr_tygodnia(int nr_tygodnia) {
        this.nr_tygodnia = nr_tygodnia;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }
}
