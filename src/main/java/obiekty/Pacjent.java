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
    private Date data_urodzeniaa;
    private String wojewodztwo;

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

    public Date getData_urodzeniaa() {
        return data_urodzeniaa;
    }

    public void setData_urodzeniaa(Date data_urodzeniaa) {
        this.data_urodzeniaa = data_urodzeniaa;
    }

    public String getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(String wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    public String getMejscowosc() {
        return mejscowosc;
    }

    public void setMejscowosc(String mejscowosc) {
        this.mejscowosc = mejscowosc;
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

    private String mejscowosc;
    private String adres;
    private String kod_pocztowy;
    private String telefon;
    private String email;

}
