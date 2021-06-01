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


    private boolean wydane;

    public int ilosc1;
    public int ilosc2;
    public int ilosc3;
    public int ilosc4;
    public int ilosc5;

    public int getIlosc1() {
        return ilosc1;
    }

    public void setIlosc1(int ilosc1) {
        this.ilosc1 = ilosc1;
    }

    public int getIlosc2() {
        return ilosc2;
    }

    public void setIlosc2(int ilosc2) {
        this.ilosc2 = ilosc2;
    }

    public int getIlosc3() {
        return ilosc3;
    }

    public void setIlosc3(int ilosc3) {
        this.ilosc3 = ilosc3;
    }

    public int getIlosc4() {
        return ilosc4;
    }

    public void setIlosc4(int ilosc4) {
        this.ilosc4 = ilosc4;
    }

    public int getIlosc5() {
        return ilosc5;
    }

    public void setIlosc5(int ilosc5) {
        this.ilosc5 = ilosc5;
    }

    public Recepty(String opis, Wyposazenie wyp1, Wyposazenie wyp2, Wyposazenie wyp3, Wyposazenie wyp4, Wyposazenie wyp5, int ilosc1, int ilosc2, int ilosc3, int ilosc4, int ilosc5, Pracownik pracownik, Pacjent pacjent) {
        this.opis = opis;
        this.wyp1 = wyp1;
        this.wyp2 = wyp2;
        this.wyp3 = wyp3;
        this.wyp4 = wyp4;
        this.wyp5 = wyp5;
        this.ilosc1 = ilosc1;
        this.ilosc2 = ilosc2;
        this.ilosc3 = ilosc3;
        this.ilosc4 = ilosc4;
        this.ilosc5 = ilosc5;
        this.pracownik = pracownik;
        this.pacjent = pacjent;
    }

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

    public Recepty(String opis, Wyposazenie wyp1, Wyposazenie wyp2, Wyposazenie wyp3, Wyposazenie wyp4, Wyposazenie wyp5, Pracownik pracownik, Pacjent pacjent) {
        this.opis = opis;
        this.wyp1 = wyp1;
        this.wyp2 = wyp2;
        this.wyp3 = wyp3;
        this.wyp4 = wyp4;
        this.wyp5 = wyp5;
        this.pracownik = pracownik;
        this.pacjent = pacjent;
        this.wydane = false;
    }

    public boolean isWydane() {
        return wydane;
    }

    public void setWydane(boolean wydane) {
        this.wydane = wydane;
    }

    public Recepty() {
    }
}
