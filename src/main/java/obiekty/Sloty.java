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

    public Sloty(Wizyty wizyta, int miesiac, int dzien, int godzina, String informacja) {
        this.wizyta = wizyta;
        this.miesiac = miesiac;
        this.dzien = dzien;
        this.godzina = godzina;
        this.informacja = informacja;
    }

    public Sloty() {
    }

    public Wizyty getWizyta() {
        return wizyta;
    }

    public void setWizyta(Wizyty wizyta) {
        this.wizyta = wizyta;
    }

    public int getMiesiac() {
        return miesiac;
    }

    public void setMiesiac(int miesiac) {
        this.miesiac = miesiac;
    }

    public int getDzien() {
        return dzien;
    }

    public void setDzien(int dzien) {
        this.dzien = dzien;
    }

    public int getGodzina() {
        return godzina;
    }

    public void setGodzina(int godzina) {
        this.godzina = godzina;
    }

    public String getInformacja() {
        return informacja;
    }

    public void setInformacja(String informacja) {
        this.informacja = informacja;
    }
}
