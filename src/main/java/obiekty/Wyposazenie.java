package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "Wyposazenie")
public class Wyposazenie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_wyposazenia;
    private String nazwa_wyposazenia;
    private String typ_wyposazenia;
    private Date data_waznosci;
    private int ilosc_pozostalych;
}
