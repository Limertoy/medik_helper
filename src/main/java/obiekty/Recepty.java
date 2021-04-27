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

    public int getId_recepty() {
        return id_recepty;
    }

    public void setId_recepty(int id_recepty) {
        this.id_recepty = id_recepty;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }
}
