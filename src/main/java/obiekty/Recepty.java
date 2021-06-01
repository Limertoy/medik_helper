package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Recepty")
public class Recepty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_recepty;
    private String opis;

    @OneToOne
    @JoinColumn(name="id_wyp1")
    private Wyposazenie wyp1;
    @OneToOne
    @JoinColumn(name="id_wyp2")
    private Wyposazenie wyp2;
    @OneToOne
    @JoinColumn(name="id_wyp3")
    private Wyposazenie wyp3;
    @OneToOne
    @JoinColumn(name="id_wyp4")
    private Wyposazenie wyp4;
    @OneToOne
    @JoinColumn(name="id_wyp5")
    private Wyposazenie wyp5;


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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String nazwa) {
        this.opis = nazwa;
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

    public Wyposazenie getWyp1() {
        return wyp1;
    }

    public void setWyp1(Wyposazenie wyp1) {
        this.wyp1 = wyp1;
    }

    public Wyposazenie getWyp2() {
        return wyp2;
    }

    public void setWyp2(Wyposazenie wyp2) {
        this.wyp2 = wyp2;
    }

    public Wyposazenie getWyp3() {
        return wyp3;
    }

    public void setWyp3(Wyposazenie wyp3) {
        this.wyp3 = wyp3;
    }

    public Wyposazenie getWyp4() {
        return wyp4;
    }

    public void setWyp4(Wyposazenie wyp4) {
        this.wyp4 = wyp4;
    }

    public Wyposazenie getWyp5() {
        return wyp5;
    }

    public void setWyp5(Wyposazenie wyp5) {
        this.wyp5 = wyp5;
    }

    public Recepty(int id_recepty, String opis, Wyposazenie wyp1, Wyposazenie wyp2, Wyposazenie wyp3, Wyposazenie wyp4, Wyposazenie wyp5, Pracownik pracownik, Pacjent pacjent) {
        this.id_recepty = id_recepty;
        this.opis = opis;
        this.wyp1 = wyp1;
        this.wyp2 = wyp2;
        this.wyp3 = wyp3;
        this.wyp4 = wyp4;
        this.wyp5 = wyp5;
        this.pracownik = pracownik;
        this.pacjent = pacjent;
    }

    public Recepty() {
    }
}
