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

    public int getId_obserwacji() {
        return id_obserwacji;
    }

    public void setId_obserwacji(int id_obserwacji) {
        this.id_obserwacji = id_obserwacji;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Lista_chorob getChoroba() {
        return choroba;
    }

    public void setChoroba(Lista_chorob choroba) {
        this.choroba = choroba;
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

    @OneToOne
    @JoinColumn(name="id_pacjenta")
    private Pacjent pacjent;
}
