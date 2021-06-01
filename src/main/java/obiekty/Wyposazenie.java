package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Wyposazenie")
public class Wyposazenie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_wyposazenia;
    private String nazwa_wyposazenia;
    private String typ_wyposazenia;
    private Date data_waznosci;
    private int ilosc_pozostalych;
    private int ilosc_wydanych;

    public int getId_wyposazenia() {
        return id_wyposazenia;
    }

    public void setId_wyposazenia(int id_wyposazenia) {
        this.id_wyposazenia = id_wyposazenia;
    }

    public String getNazwa_wyposazenia() {
        return nazwa_wyposazenia;
    }

    public void setNazwa_wyposazenia(String nazwa_wyposazenia) {
        this.nazwa_wyposazenia = nazwa_wyposazenia;
    }

    public String getTyp_wyposazenia() {
        return typ_wyposazenia;
    }

    public void setTyp_wyposazenia(String typ_wyposazenia) {
        this.typ_wyposazenia = typ_wyposazenia;
    }

    public Date getData_waznosci() {
        return data_waznosci;
    }

    public void setData_waznosci(Date data_waznosci) {
        this.data_waznosci = data_waznosci;
    }

    public int getIlosc_pozostalych() {
        return ilosc_pozostalych;
    }

    public void setIlosc_pozostalych(int ilosc_pozostalych) {
        this.ilosc_pozostalych = ilosc_pozostalych;
    }

    public Wyposazenie(String nazwa_wyposazenia, String typ_wyposazenia, Date data_waznosci, int ilosc_pozostalych) {
        this.nazwa_wyposazenia = nazwa_wyposazenia;
        this.typ_wyposazenia = typ_wyposazenia;
        this.data_waznosci = data_waznosci;
        this.ilosc_pozostalych = ilosc_pozostalych;
    }

    public int getIlosc_wydanych() {
        return ilosc_wydanych;
    }

    public void setIlosc_wydanych(int ilosc_wydanych) {
        this.ilosc_wydanych = ilosc_wydanych;
    }

    @Override
    public String toString() {
        return this.nazwa_wyposazenia;
    }


    public Wyposazenie() {
    }

}
