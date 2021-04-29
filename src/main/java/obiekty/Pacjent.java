package obiekty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "Pacjent")
public class Pacjent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_pacjenta;

    private String nazwisko_pacjenta;
    private String imie_pacjenta;
    private String drugie_imie;
    private String pesel;
    private String plec;
    private Date data_urodzenia;
    private String wojewodztwo;
    private String miejscowosc;
    private String adres;
    private String kod_pocztowy;
    private String telefon;
    private String email;

    public int getId_pacjenta() {
        return id_pacjenta;
    }

    public void setId_pacjenta(int id_pacjenta) {
        this.id_pacjenta = id_pacjenta;
    }

    public String getNazwisko_pacjenta() {
        return nazwisko_pacjenta;
    }

    public void setNazwisko_pacjenta(String nazwisko_pacjenta) {
        this.nazwisko_pacjenta = nazwisko_pacjenta;
    }

    public String getImie_pacjenta() {
        return imie_pacjenta;
    }

    public void setImie_pacjenta(String imie_pacjenta) {
        this.imie_pacjenta = imie_pacjenta;
    }

    public String getDrugie_imie() {
        return drugie_imie;
    }

    public void setDrugie_imie(String drugie_imie) {
        this.drugie_imie = drugie_imie;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public Date getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(Date data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public String getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(String wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pacjent(
                   String nazwisko_pacjenta,
                   String imie_pacjenta,
                   String drugie_imie,
                   String pesel,
                   String plec,
                   Date data_urodzenia,
                   String wojewodztwo,
                   String miejscowosc,
                   String adres,
                   String kod_pocztowy,
                   String telefon,
                   String email) {
        this.nazwisko_pacjenta = nazwisko_pacjenta;
        this.imie_pacjenta = imie_pacjenta;
        this.drugie_imie = drugie_imie;
        this.pesel = pesel;
        this.plec = plec;
        this.data_urodzenia = data_urodzenia;
        this.wojewodztwo = wojewodztwo;
        this.miejscowosc = miejscowosc;
        this.adres = adres;
        this.kod_pocztowy = kod_pocztowy;
        this.telefon = telefon;
        this.email = email;
    }

    public Pacjent() {}

    @Override
    public String toString() {
        return "Pacjent{" +
                "id_pacjenta=" + id_pacjenta +
                ", nazwisko_pacjenta='" + nazwisko_pacjenta + '\'' +
                ", imie_pacjenta='" + imie_pacjenta + '\'' +
                ", drugie_imie='" + drugie_imie + '\'' +
                ", pesel='" + pesel + '\'' +
                ", plec='" + plec + '\'' +
                ", data_urodzenia=" + data_urodzenia +
                ", wojewodztwo='" + wojewodztwo + '\'' +
                ", miejscowosc='" + miejscowosc + '\'' +
                ", adres='" + adres + '\'' +
                ", kod_pocztowy='" + kod_pocztowy + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
