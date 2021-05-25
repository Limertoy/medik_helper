package DAO;

import obiekty.Obserwacja;

import java.util.List;

public interface ObserwacjaDAO {

    Obserwacja save(Obserwacja  entity);
    void remove(Obserwacja entity);
    Obserwacja findById(int id);
    List<Obserwacja> findAll();
    List<Obserwacja> findAllPacjent(int id);
}
