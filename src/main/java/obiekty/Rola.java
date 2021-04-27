package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Rola")
public class Rola {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_rola;
    private String nazwa;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getId_rola() {
        return id_rola;
    }

    public void setId_rola(int id_rola) {
        this.id_rola = id_rola;
    }

    @Override
    public String toString() {
        return "Rola{" +
                "id_rola=" + id_rola +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}
