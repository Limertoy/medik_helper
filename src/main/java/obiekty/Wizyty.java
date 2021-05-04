package obiekty;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Wizyty")
public class Wizyty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id_wizyty;
    @OneToOne
    @JoinColumn(name="id_slota")
    private Sloty slot;
    @OneToOne
    @JoinColumn(name="id_pacjenta")
    private Pacjent pacjent;

    public Wizyty() {
    }

    public Wizyty(Sloty slot, Pacjent pacjent) {
        this.slot = slot;
        this.pacjent = pacjent;
    }

    public int getId_wizyty() {
        return id_wizyty;
    }

    public void setId_wizyty(int id_wizyty) {
        this.id_wizyty = id_wizyty;
    }

    public Sloty getSlot() {
        return slot;
    }

    public void setSlot(Sloty slot) {
        this.slot = slot;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }
}
