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
    private String mejscowosc;
    private String adres;
    private String kod_pocztowy;
    private String telefon;
    private String email;

}
