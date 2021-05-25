package obiekty;

import com.mycompany.PDFGenerator.Mappable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity(name = "Obserwacje")
public class Obserwacja implements Mappable, Serializable {

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
    private Integer jeden;

    public Obserwacja(String tekst, Lista_chorob choroba, Pracownik pracownik, Pacjent pacjent) {
        this.tekst = tekst;
        this.choroba = choroba;
        this.pracownik = pracownik;
        this.pacjent = pacjent;
        this.jeden = 1;
    }

    public Obserwacja() {
    }

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

    public Integer getJeden() {
        return jeden;
    }

    public void setJeden(Integer jeden) {
        this.jeden = jeden;
    }

    @Override
    public String toString() {
        return "Obserwacja{" +
                "id_obserwacji=" + id_obserwacji +
                ", tekst='" + tekst + '\'' +
                ", choroba=" + choroba +
                ", pracownik=" + pracownik +
                ", pacjent=" + pacjent +
                ", jeden=" + jeden +
                '}' + "\n";
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Imię pracownika", pracownik.toString());
        map.put("Liczba wypisanych obserwacji", jeden.toString());
        return map;
    }
}
