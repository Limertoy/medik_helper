package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "Sloty")
public class Sloty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_slota;
    @OneToOne
    @JoinColumn(name="id_wizyty")
    private Wizyty wizyta;
    private LocalDate data;
    private String godzina;
    private String informacja;
    @OneToOne
    @JoinColumn(name = "id_pracownika")
    private Pracownik pracownik;

    public Sloty(LocalDate data, String godzina, String informacja, Pracownik pracownik) {
        this.data = data;
        this.godzina = godzina;
        this.informacja = informacja;
        this.pracownik = pracownik;
    }

    public int getId_slota() {
        return id_slota;
    }

    public void setId_slota(int id_slota) {
        this.id_slota = id_slota;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public Sloty() {
    }

    public Wizyty getWizyta() {
        return wizyta;
    }

    public void setWizyta(Wizyty wizyta) {
        this.wizyta = wizyta;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getGodzina() {
        return godzina;
    }

    public void setGodzina(String godzina) {
        this.godzina = godzina;
    }

    public String getInformacja() {
        return informacja;
    }

    public void setInformacja(String informacja) {
        this.informacja = informacja;
    }

    @Override
    public String toString() {
        return "Sloty{" +
                "id_slota=" + id_slota +
                ", wizyta=" + wizyta +
                ", data=" + data +
                ", godzina=" + godzina +
                ", informacja='" + informacja + '\'' +
                ", pracownik=" + pracownik +
                '}';
    }
}
