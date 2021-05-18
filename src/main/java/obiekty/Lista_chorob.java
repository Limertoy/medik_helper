package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Lista_chorob")
public class Lista_chorob {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_choroby;
    private String kod_choroby;
    private String nazwa_choroby;

    public Lista_chorob() {

    }

    public int getId_choroby() {
        return id_choroby;
    }

    public void setId_choroby(int id_choroby) {
        this.id_choroby = id_choroby;
    }

    public String getKod_choroby() {
        return kod_choroby;
    }

    public void setKod_choroby(String kod_choroby) {
        this.kod_choroby = kod_choroby;
    }

    public String getNazwa_choroby() {
        return nazwa_choroby;
    }

    public void setNazwa_choroby(String nazwa_choroby) {
        this.nazwa_choroby = nazwa_choroby;
    }

    public Lista_chorob(String kod_choroby, String nazwa_choroby) {
        this.kod_choroby = kod_choroby;
        this.nazwa_choroby = nazwa_choroby;
    }

    @Override
    public String toString() {
        return nazwa_choroby;
    }
}
