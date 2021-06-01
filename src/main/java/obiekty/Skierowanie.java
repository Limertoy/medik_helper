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

    public Skierowanie() {

    }

    public int getId_skierowania() {
        return id_skierowania;
    }

    public void setId_skierowania(int id_skierowania) {
        this.id_skierowania = id_skierowania;
    }

    public String getPoradnia() {
        return poradnia;
    }

    public void setPoradnia(String poradnia) {
        this.poradnia = poradnia;
    }

    public String getRozpoznanie() {
        return rozpoznanie;
    }

    public void setRozpoznanie(String rozpoznanie) {
        this.rozpoznanie = rozpoznanie;
    }

    public String getInformacje() {
        return informacje;
    }

    public void setInformacje(String informacje) {
        this.informacje = informacje;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }

    public Skierowanie (String poradnia, String rozpoznanie, String informacje, Pacjent pacjent) {
        this.poradnia = poradnia;
        this.rozpoznanie = rozpoznanie;
        this.informacje = informacje;
        this.pacjent = pacjent;
    }
}
