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
}
