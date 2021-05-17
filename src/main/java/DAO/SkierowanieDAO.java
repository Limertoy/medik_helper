package DAO;

import obiekty.Rola;
import obiekty.Skierowanie;

import java.util.List;

public interface SkierowanieDAO {

    Skierowanie save(Skierowanie entity);
    void remove(Skierowanie entity);
    Skierowanie findById(int id);
    List<Skierowanie> findAll();

}
