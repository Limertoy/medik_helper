package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Pracownik")
public class Pracownik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_pracownika;
    private String email;
    private String haslo;
    @OneToOne
    @JoinColumn(name="id_rola")
    private Rola rola;
    private String imie_pracownika;
    private String nazwisko_pracownika;

    public int getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika = id_pracownika;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public Rola getRola() {
        return rola;
    }

    public void setRola(Rola rola) {
        this.rola = rola;
    }

    public String getImie_pracownika() {
        return imie_pracownika;
    }

    public void setImie_pracownika(String imie_pracownika) {
        this.imie_pracownika = imie_pracownika;
    }

    public String getNazwisko_pracownika() {
        return nazwisko_pracownika;
    }

    public void setNazwisko_pracownika(String nazwisko_pracownika) {
        this.nazwisko_pracownika = nazwisko_pracownika;
    }

    public Pracownik() {}

    public Pracownik(String email, String haslo, Rola rola, String imie_pracownika, String nazwisko_pracownika) {
        this.email = email;
        this.haslo = haslo;
        this.rola = rola;
        this.imie_pracownika = imie_pracownika;
        this.nazwisko_pracownika = nazwisko_pracownika;
    }

    @Override
    public String toString() {
        return imie_pracownika + " " + nazwisko_pracownika;
    }
}
